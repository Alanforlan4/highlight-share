import { Button } from '@/components/ui/button'

export default function Home() {
  return (
    <main className="flex h-screen items-center justify-center bg-zinc-950">
      <div className="text-center space-y-4">
        <h1 className="text-white text-2xl">Teste do Shadcn</h1>
        <Button variant="default">Click Me</Button>
      </div>
    </main>
  )
}