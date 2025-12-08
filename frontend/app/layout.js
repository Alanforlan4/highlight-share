import { Inter } from "next/font/google";
import "./globals.css";
import AppLayout from "@/components/layout/AppLayout"; 

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "HighlightShare",
  description: "Compartilhe seus lances",
};

export default function RootLayout({ children }) {
  return (
    <html lang="pt-br">
      <body className={inter.className}>
        <AppLayout>
          {children}
        </AppLayout>
      </body>
    </html>
  );
}