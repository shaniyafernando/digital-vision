import { Address } from "./Address";
import { Cart } from "./cart";
import { WishList } from "./WishList";

export interface User{
    id: number;
    username: String;
    email: String;
    password: String;
    role: String;
    cart: Cart;
    wishList: WishList;
    address: Address;
}