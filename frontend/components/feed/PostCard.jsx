import Image from "next/image";
import { Avatar, AvatarFallback, AvatarImage } from "../ui/avatar";
import { Button } from "../ui/button";
import { Heart, MessageCircle } from "lucide-react";

export function PostCard({ post }) {
  return (
    <article className="border-b md:border md:rounded-xl bg-card w-full mb-4 py-2">
      {/* Header with user info */}
      <div className="flex items-center justify-between p-3">
        <div className="flex items-center gap-3">
          <Avatar className="w-8 h-8 mt-1">
            <AvatarImage
              src={post.user.avatar}
              alt={post.user.name}
            ></AvatarImage>
            <AvatarFallback>{post.user.name[0]}</AvatarFallback>
          </Avatar>
          <div className="flex flex-col">
            <span className="font-semibold text-sm text-left">
              {post.user.username}
            </span>
            {post.location && (
              <span className="text-xs text-muted-foreground">
                {post.location}
              </span>
            )}
          </div>
        </div>
        <span className="text-xs text-muted-foreground">{post.timestamp}</span>
      </div>

      {/* Image */}
      <div className="relative w-full aspect-square bg-muted">
        <Image
          src={post.imageUrl}
          alt="Post content"
          fill
          className="object-cover"
        />
      </div>

      {/* Action Bar */}
      <div className="pb-0 flex items-center gap-1">
        <Button
          variant="ghost"
          size="icon"
          className="hover:text-red-500 [&_svg]:size-6"
        >
          <Heart />
        </Button>
        <Button variant="ghost" size="icon" className="[&_svg]:size-6">
          <MessageCircle />
        </Button>
      </div>

      {/* Text */}
      <div className="p-3 pt-1 space-y-1">
        <div className="font-semibold text-sm text-left">
          {post.likes} curtidas
        </div>
        <div className="text-sm">
          <span className="font-semibold mr-2">{post.user.username}</span>
          <span>{post.caption}</span>
        </div>
      </div>
    </article>
  );
}
