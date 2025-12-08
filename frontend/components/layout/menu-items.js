import { Bell, Home, PlusSquare, User, Users } from "lucide-react";

export const menuItems = [
  {
    label: "Feed",
    href: "/",
    icon: Home
  },
  {
    label: "Grupos",
    href: "/groups",
    icon: Users
  },
  {
    label: "Postar",
    href: "/post",
    icon: PlusSquare,
    isAction: true
  },
  {
    label: "Notificações",
    href: "/notifications",
    icon: Bell
  },
  {
    label: "Perfil",
    href: "/profile",
    icon: User
  },
]