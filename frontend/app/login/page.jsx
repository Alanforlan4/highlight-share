"use client";

import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { Ghost, Loader2, User } from "lucide-react";
import { useRouter } from "next/navigation";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";

const loginSchema = z.object({
  username: z.string().min(1, "Digite um usuário válido"),
  password: z.string().min(1, "A senha é obrigatória"),
});

const registerSchema = z.object({
  username: z.string().min(3, "O usuário deve ter pelo menos 3 caracteres"),
  password: z.string().min(6, "A senha deve ter no mínimo 6 caracteres"),
});

export default function AuthPage() {
  const router = useRouter();
  const [isLoading, setIsLoading] = useState(false);

  // Login Form
  const {
    register: registerLogin,
    handleSubmit: handleSubmitLogin,
    formState: { errors: errorsLogin },
  } = useForm({
    resolver: zodResolver(loginSchema),
  });

  // SignUp Form
  const {
    register: registerSignUp,
    handleSubmit: handleSubmitSignup,
    formState: { errors: errorsSignup },
  } = useForm({
    resolver: zodResolver(registerSchema),
  });

  async function onLogin(data) {
    setIsLoading(true);
    // todo: integração com backend
  }

  async function onSignup(data) {
    setIsLoading(true);
    //TODO: integração com backend
  }

  return (
    <div className="flex min-h-screen items-center justify-center bg-muted p-4">
      <Card className="w-full max-w-md">
        <CardHeader className="text-center">
          <div className="mx-auto mb-2 flex h-12 w-12 items-center justify-center rounded-full bg-primary/10">
            <Ghost className="h-6 w-6 text-primary" />
          </div>
          <CardTitle className="text-2xl">HighlightShare</CardTitle>
          <CardDescription>Entre para compartilhar seus lances</CardDescription>
        </CardHeader>

        <CardContent>
          <Tabs defaultValue="login" className="w-full">
            <TabsList className="grid w-full grid-cols-2 mb-4">
              <TabsTrigger value="login">Entrar</TabsTrigger>
              <TabsTrigger value="register">Cadastrar</TabsTrigger>
            </TabsList>

            {/* Tab de login */}
            <TabsContent value="login">
              <form onSubmit={handleSubmitLogin(onLogin)} className="space-y-4">
                <div className="space-y-2">
                  <Label htmlFor="login-username">Usuário</Label>
                  <div className="relative">
                    <User className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
                    <Input
                      id="login-username"
                      placeholder="Seu usuário"
                      className="pl-9"
                      {...registerLogin("username")}
                    />
                  </div>
                  {errorsLogin.username && (
                    <p className="text-xs text-red-500">
                      {errorsLogin.username.message}
                    </p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label htmlFor="login-password">Senha</Label>
                  <Input
                    id="login-password"
                    type="password"
                    {...registerLogin("password")}
                  />
                  {errorsLogin.password && (
                    <p className="text-xs text-red-500">
                      {errorsLogin.password.message}
                    </p>
                  )}
                </div>

                <Button type="submit" className="w-full" disabled={isLoading}>
                  {isLoading ? (
                    <>
                      <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                      Entrando...
                    </>
                  ) : (
                    "Acessar conta"
                  )}
                </Button>
              </form>
            </TabsContent>

            {/* Tab de cadastro */}
            <TabsContent value="register">
              <form
                onSubmit={handleSubmitSignup(onSignup)}
                className="space-y-4"
              >
                <div className="space-y-2">
                  <Label htmlFor="signup-username">Usuário</Label>
                  <div className="relative">
                    <User className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
                    <Input
                      id="signup-username"
                      placeholder="Escolha um nome de usuário único"
                      className="pl-9"
                      {...registerSignUp("username")}
                    />
                  </div>
                  {errorsLogin.username && (
                    <p className="text-xs text-red-500">
                      {errorsLogin.username.message}
                    </p>
                  )}
                </div>

                <div className="space-y-2">
                  <Label htmlFor="signup-password">Senha</Label>
                  <Input
                    id="signup-password"
                    type="password"
                    placeholder="Mínimo de 6 caracteres"
                    {...registerSignUp("password")}
                  />
                  {errorsLogin.password && (
                    <p className="text-xs text-red-500">
                      {errorsLogin.password.message}
                    </p>
                  )}
                </div>

                <Button type="submit" className="w-full" disabled={isLoading}>
                  {isLoading ? (
                    <>
                      <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                      Criando conta...
                    </>
                  ) : (
                    "Criar conta"
                  )}
                </Button>
              </form>
            </TabsContent>
          </Tabs>
        </CardContent>
      </Card>
    </div>
  );
}
