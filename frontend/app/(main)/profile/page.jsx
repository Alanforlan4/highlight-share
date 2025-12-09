"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { LogOut, Calendar } from "lucide-react";

import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";

export default function ProfilePage() {
  const router = useRouter();
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(null);

  useEffect(() => {
    const fetchProfile = async () => {
      const token = localStorage.getItem("token");
      
      if (!token) {
        // router.push("/login") Comentado para facilitar seus testes
      }

      // TODO: INTEGRAR COM BACKEND
      // const res = await fetch('http://localhost:8080/users/me', {
      //   headers: { Authorization: `Bearer ${token}` }
      // })
      // const data = await res.json()

      // MOCK UserEntity.java
      setTimeout(() => {
        setUser({
          id: 1,
          username: "boleiro_10", // Vem do UserEntity.username
          displayName: "Camisa 10", // Vem do UserEntity.displayName
          avatarUrl: "https://github.com/shadcn.png", // Vem do UserEntity.avatarUrl
          email: "boleiro@ufrn.br", // Vem do UserEntity.email
          createdAt: "2024-05-12T10:00:00", // Vem do UserEntity.createdAt
          role: "MEMBER",
        });
        setIsLoading(false);
      }, 1000);
    };

    fetchProfile();
  }, [router]);

  const handleLogout = () => {
    localStorage.removeItem("token")
    localStorage.removeItem("highlightshare_user")
    router.push("/login")
  }

  const formatDate = (dateString) => {
    if (!dateString) return ""
    const date = new Date(dateString)
    return new Intl.DateTimeFormat('pt-BR', { month: 'long', year: 'numeric' }).format(date)
  }

  if (isLoading) {
    return <div className="flex h-screen items-center justify-center">Carregando perfil...</div>
  }

  return (
    <div className="flex flex-col min-h-screen">
      <div className="p-6 pb-2">
        <div className="flex flex-col items-center gap-4">
          <Avatar className='h-24 w-24 border-2 border-primary'>
            <AvatarImage src={user?.avatarUrl} alt={user?.displayName} />
            <AvatarFallback className='text-2xl'>{user?.displayName?.[0]}</AvatarFallback>
          </Avatar>

          <div className="text-center space-y-1">
            <h1 className="text-2xl font-bold">{user?.displayName}</h1>
            <p className="text-muted-foreground">{user?.username}</p>
          </div>

          <div className="mt-4 flex items-center justify-center gap-2 text-xs text-muted-foreground">
            <Calendar className="h-3 2-3"/>
            <span>Membro desde {formatDate(user?.createdAt)}</span>
          </div>

          <div className="mt-6 flex gap-2">
            <Button variant='destructive' size='icon' onClick={handleLogout} className='text-white'>
              <LogOut className="h-4 w-4"/>
            </Button>
          </div>
        </div>
      </div>
    </div>
  )

}
