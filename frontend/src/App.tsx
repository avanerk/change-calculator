import { useState, useEffect, FormEvent } from "react";
import { Input } from "./components/ui/input";
import { Button } from "./components/ui/button";
import { Card, CardContent } from "./components/ui/card";
import { cn } from "@/lib/utils";

interface CurrencyInfo {
  code: string;
  symbol: string;
}

export default function App() {
  const [amount, setAmount] = useState<string>("");
  const [paid, setPaid] = useState<string>("");
  const [currency, setCurrency] = useState<string>("EUR");
  const [currencies, setCurrencies] = useState<CurrencyInfo[]>([]);
  const [symbol, setSymbol] = useState<string>("€");
  const [result, setResult] = useState<Record<string, number> | null>(null);
  const [error, setError] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  const apiUrl = process.env.REACT_APP_API_URL || "http://localhost:8000";

  useEffect(() => {
    fetch(`${apiUrl}/api/v1/currencies/info`)
      .then((res) => res.json())
      .then((data) => {
        setCurrencies(data);
        const selected = data.find((c: CurrencyInfo) => c.code === currency);
        if (selected) setSymbol(selected.symbol);
      });
  }, []);

  useEffect(() => {
    const selected = currencies.find((c) => c.code === currency);
    if (selected) {
      setSymbol(selected.symbol);
    }
  }, [currency, currencies]);

  const formatCurrency = (cents: string) => {
    const value = (parseInt(cents) / 100).toFixed(2);
    return `${symbol} ${value.replace(".", ",")}`;
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError("");
    setResult(null);

    const amountCents = Math.round(parseFloat(amount) * 100);
    const paidCents = Math.round(parseFloat(paid) * 100);

    if (isNaN(amountCents) || isNaN(paidCents)) {
      return setError("Beide velden moeten geldige getallen zijn.");
    }

    if (paidCents < amountCents) {
      return setError("Het betaalde bedrag is te laag.");
    }

    setLoading(true);
    try {
      const response = await fetch(`${apiUrl}/api/v1/change`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ amount: amountCents, paid: paidCents, currency }),
      });

      if (!response.ok) {
        throw new Error("Fout bij ophalen van wisselgeld.");
      }

      const data: Record<string, number> = await response.json();
      setResult(data);
    } catch (err) {
      setError("Kon geen verbinding maken met de backend.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex flex-col bg-gray-100">
      <header className="bg-black text-white shadow py-4">
        <div className="max-w-5xl mx-auto flex items-center justify-between px-4">
          <div className="flex items-center space-x-3">
            <img src="/logo.svg" alt="Logo" className="h-10" />
            <h1 className="text-2xl font-bold">Wisselgeld Calculator</h1>
          </div>
        </div>
      </header>

      <main className="flex-1 flex items-start justify-center px-4 py-10">
        <div className="w-full max-w-5xl grid grid-cols-1 md:grid-cols-2 gap-8">
          <Card className="p-6 bg-white shadow-md">
            <form onSubmit={handleSubmit} className="space-y-4">
              <div>
                <label className="block text-sm font-medium mb-1">Valuta</label>
                <select
                  value={currency}
                  onChange={(e) => setCurrency(e.target.value)}
                  className="w-full p-2 border rounded-md shadow-sm bg-white"
                >
                  {currencies.map((c) => (
                    <option key={c.code} value={c.code}>{c.code}</option>
                  ))}
                </select>
              </div>
              <div>
                <label className="block text-sm font-medium mb-1">Aankoopbedrag ({symbol})</label>
                <Input
                  type="number"
                  step="0.01"
                  placeholder="12.50"
                  value={amount}
                  onChange={(e) => setAmount(e.target.value)}
                  className="bg-white shadow-sm"
                />
              </div>
              <div>
                <label className="block text-sm font-medium mb-1">Betaald bedrag ({symbol})</label>
                <Input
                  type="number"
                  step="0.01"
                  placeholder="20.00"
                  value={paid}
                  onChange={(e) => setPaid(e.target.value)}
                  className="bg-white shadow-sm"
                />
              </div>

              {error && <p className="text-red-600 text-sm">{error}</p>}

              <Button
                type="submit"
                className="w-full bg-black text-white font-semibold py-2 rounded-md hover:bg-gray-800 transition-colors"
                disabled={loading}
              >
                {loading ? "Berekenen..." : "Bereken wisselgeld"}
              </Button>
            </form>
          </Card>

          {result && (
            <Card className="p-6 bg-white shadow-md">
              <CardContent className="space-y-2">
                <h2 className="text-lg font-semibold">Wisselgeld</h2>
                <ul className="text-sm space-y-1">
                  {Object.entries(result).map(([key, value]) => (
                    <li key={key} className="flex justify-between">
                      <span>{formatCurrency(key)}</span>
                      <span>{value} stuks</span>
                    </li>
                  ))}
                </ul>
              </CardContent>
            </Card>
          )}
        </div>
      </main>

      <footer className="text-center text-sm text-gray-500 py-4">
        Omoda wisselgeld calculator ontwikkeld door Arend van Erk als onderdeel van het assessement
      </footer>
    </div>
  );
}