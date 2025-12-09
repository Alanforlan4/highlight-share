"use client";

import { PostCard } from "@/components/feed/PostCard";
import { MOCK_POSTS } from "../data/mock-posts";
import { useState } from "react";
import { Ghost } from "lucide-react";
import { Button } from "@/components/ui/button";

const ITEMS_PER_PAGE = 2; // poucos itens para testar botão de load

export default function FeedPage() {
  const [displayPosts, setDisplayPosts] = useState(
    MOCK_POSTS.slice(0, ITEMS_PER_PAGE)
  );
  const [page, setPage] = useState(1);

  const hasMore = displayPosts.length < MOCK_POSTS.length;

  const handleLoadMore = () => {
    const nextPage = page + 1;
    const newPosts = MOCK_POSTS.slice(0, nextPage * ITEMS_PER_PAGE);
    setDisplayPosts(newPosts);
    setPage(nextPage);
  };

  return (
    <main className="flex flex-col items-center">
      <div className="w-full space-y-4">
        {displayPosts.map((post) => (
          <PostCard key={post.id} post={post} />
        ))}
      </div>

      {/* Empty State: cenário sem nenhum post */}
      {displayPosts.length === 0 && (
        <div className="flex flex-col items-center justify-center py-10 text-center text-muted-foreground">
          <Ghost className="h-12 w-12 mb-2 opacity-50" />
          <p>Nenhum post encontrado.</p>
          <p className="text-sm">Entre em um grupo!</p>
        </div>
      )}

      {hasMore && (
        <div className="py-8">
          <Button
            variant="outline"
            onClick={handleLoadMore}
            className="min-w-[150px]"
          >
            Carregar Mais
          </Button>
        </div>
      )}

      {!hasMore && displayPosts.length > 0 && (
        <div className="py-8 text-xs text-muted-foreground">
          Você chegou ao fim!
        </div>
      )}
    </main>
  );
}
