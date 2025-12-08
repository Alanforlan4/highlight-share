'use client'

import { Ghost } from "lucide-react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { menuItems } from "./menu-items";

export default function AppLayout({ children }) {
  const pathname = usePathname();

  return (
    <div className="min-h-screen bg-background flex flex-col md:flex-row">
      {/* Mobile Top Bar */}
      <header className="md:hidden sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60 h-14 flex items-center justify-between px-4">
        <div className="flex items-center gap-2 font-bold text-lg">
          <Ghost className="h-6 w-6" />
          <span>HighlightShare</span>
        </div>
        <div className="h-8 w-8 bg-zinc-200 rounded-full flex items-center justify-center">
          <span className="text-xs">Eu</span>
        </div>
      </header>

      {/* Desktop Side bar */}
      <aside className="hidden md:flex flex-col w-64 border-r h-screen sticky top-0 bg-card p-4">
        <div className="flex items-center gap-2 font-bold text-xl mb-8 px-2">
          <Ghost className="h-8 w-8" />
          <span>HighlightShare</span>
        </div>

        <nav className="flex flex-col gap-2">
          {menuItems.map((item) => {
            const isActive = pathname === item.href;
            const Icon = item.icon;

            return (
              <Link
                key={item.href}
                href={item.href}
                className={`flex items-center gap-3 px-3 py-3 rounded-lg transition-colors ${
                  isActive
                    ? "bg-primary text-primary-foreground font-medium"
                    : "hover:bg-muted text-muted-foreground"
                }`}
              >
                <Icon size={22} />
                <span>{item.label}</span>
              </Link>
            );
          })}
        </nav>
      </aside>

      <main className="flex-1 w-full max-w-2xl mx-auto p-4 pb-24 md:pb-4 md:pt-8">
        {children}
      </main>

      {/* Mobile Bottom Nav */}
      <nav className="md:hidden fixed bottom-0 left-0 right-0 border-t bg-background h-16 flex items-center justify-around z-50 pb-safe">
        {menuItems.map((item) => {
          const isActive = pathname === item.href;
          const Icon = item.icon;

          return (
            <Link
              key={item.href}
              href={item.href}
              className={`flex flex-col items-center justify-center w-full h-full space-y-1 ${
                isActive ? "text-primary" : "text-muted-foreground"
              }`}
            >
              <Icon
                size={item.isAction ? 26 : 22}
                className={isActive ? "fill-current" : ""}
              />
              <span className="text-[10px] font-medium">{item.label}</span>
            </Link>
          );
        })}
      </nav>
    </div>
  );
}
