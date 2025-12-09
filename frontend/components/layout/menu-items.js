import { Home, PlusSquare, User } from "lucide-react";

export const menuItems = [
  {
    label: "Feed",
    href: "/",
    icon: Home
  },
  {
    label: "Postar",
    href: "/post",
    icon: PlusSquare,
    isAction: true
  },
  {
    label: "Perfil",
    href: "/profile",
    icon: User
  },
]