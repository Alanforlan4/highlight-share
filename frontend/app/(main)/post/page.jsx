"use client";

import { useState, useRef, useEffect } from "react";
import { useRouter } from "next/navigation";
import Image from "next/image";
import { UploadCloud, X, MapPin, Loader2, Loader } from "lucide-react";

import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Card, CardContent } from "@/components/ui/card";

export default function CreatePostPage() {
  const router = useRouter();
  const fileInputRef = useRef(null);

  const [file, setFile] = useState(null);
  const [previewUrl, setPreviewUrl] = useState(null);
  const [caption, setCaption] = useState("");
  const [location, setLocation] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [token, setToken] = useState(null);

  useEffect(() => {
    // pega o token no local storage
    const storedToken = localStorage.getItem("token");
    if (!storedToken) {
      router.push("/login");
    } else {
      setToken(storedToken);
    }
  }, [router]);

  const handleFileSelect = (e) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) {
      setFile(selectedFile);
      const url = URL.createObjectURL(selectedFile);
      setPreviewUrl(url);
    }
  };

  const handleRemoveImage = () => {
    setFile(null);
    setPreviewUrl(null);
    if (fileInputRef.current) fileInputRef.current.value = "";
  };

  const handleSubmit = async () => {
    if (!file) return alert("Selecione uma foto primeiro");
    if (!token) return alert("Erro de autenticação. Faça login novamente");

    setIsLoading(true);

    try {
      const formData = new FormData();

      formData.append("file", file);
      formData.append("caption", caption);
      formData.append("location", location);

      // TODO verificar assinatura da api correta
      const response = await fetch("http://localhost:8080/posts", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: formData,
      });

      if (!response.ok) {
        if (response.status === 403 || response.status === 401) {
          alert("Sessão expirada. Faça login novamente");
          router.push("/login");
          return;
        }
        throw new Error("Erro da resposta da API");
      }

      alert("Post criado com sucesso");
      router.push("/");
      router.refresh();
    } catch (error) {
      console.error("Falha no upload: ", error);
      alert("Falha ao criar post");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl font-bold mb-6 px-2">Novo Post</h1>

      <div className="space-y-6">
        <Card className="overflow-hidden">
          <CardContent className="p-0">
            {previewUrl ? (
              <div className="relative aspect-square w-full bg-zinc-100">
                <Image
                  src={previewUrl}
                  alt="Preview"
                  fill
                  className="object-cover"
                />
                <Button
                  variant="destructive"
                  size="icon"
                  className="absolute top-2 right-2 rounded-full h-8 w-8"
                  onClick={handleRemoveImage}
                >
                  <X className="h-4 w-4" />
                </Button>
              </div>
            ) : (
              <div
                onClick={() => fileInputRef.current?.click()}
                className="aspect-square w-full flex flex-col items-center justify-center bg-muted/50 border-2 border-dashed hover:bg-muted cursor-pointer transition-colors"
              >
                <div className="flex flx-col items-center gap-2 text-muted-foreground">
                  <div className="p-4 bg-bakcground rounded-full shadow-sm">
                    <UploadCloud className="h-8 w-8" />
                  </div>
                  <span className="font-medium">
                    Toque para selecionar a foto
                  </span>
                  <span className="text-xs">JPG ou PNG</span>
                </div>
              </div>
            )}
          </CardContent>
        </Card>

        <input
          type="file"
          accept="image/*"
          className="hidden"
          ref={fileInputRef}
          onChange={handleFileSelect}
        />

        <div className="space-y-4 px-1">
          <div className="space-y-2">
            <Label htmlFor="caption">Legenda</Label>
            <Textarea
              id="caption"
              placeholder="Escreva algo sobre o lance..."
              className="resize-none min-h-[100px]"
              value={caption}
              onChange={(e) => setCaption(e.target.value)}
            />
          </div>

          <div className="space-y-2">
            <Label htmlFor="location">Localização</Label>
            <div className="relative">
              <MapPin className="absolute left-3 top-3 h-4 w-4 text-muted-foreground" />
              <Input
                id="location"
                placeholder="Ex: Quadra de Capim Macio"
                className="pl-9"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
              />
            </div>
          </div>

          <Button
            className="w-full h-12 text-lg"
            onClick={handleSubmit}
            disabled={!file || isLoading}
          >
            {isLoading ? (
              <>
                <Loader2 className="mr-2 h-5 w-5 animate-spin" />
                Publicando...
              </>
            ) : (
              "Compartilhar"
            )}
          </Button>
        </div>
      </div>
    </div>
  );
}
