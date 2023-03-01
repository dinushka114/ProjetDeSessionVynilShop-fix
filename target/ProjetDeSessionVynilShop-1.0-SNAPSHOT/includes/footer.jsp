<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script>

//cart

    let count = 0;
    let sum = 0;
    let cart = {};

    if (localStorage.getItem("count")) {
        count = parseInt(localStorage.getItem("count"));
    }

    if (localStorage.getItem("sum")) {
        sum = parseInt(localStorage.getItem("sum"));
    }

    if (localStorage.getItem("cart")) {
        cart = JSON.parse(localStorage.getItem("cart"));
    }

    updateCart();

    function updateCart() {
        document.getElementById("count").textContent = count;
        document.getElementById("sum").textContent = sum.toFixed(2);
        localStorage.setItem("count", count);
        localStorage.setItem("sum", sum);
    }

    function addToCart(itemId, itemPrice, itemName, itemImage) {
        let price = Number(itemPrice);
        let title = itemName;
        let id = itemId;

        if (id in cart) {
            cart[id].qty++;
        } else {
            let cartItem = {
                title: title,
                price: price,
                image: itemImage,
                qty: 1,
                id: id
            };
            cart[id] = cartItem;
        }

        count++;
        sum += price;

        console.log(cart);

        localStorage.setItem("cart", JSON.stringify(cart));
        updateCart();
        alert("Item added to the cart");
    }

    function removeFromCart(itemId, itemPrice, itemName, itemImage) {
        let price = Number(itemPrice);
        let title = itemName;
        let id = itemId;

        if (id in cart) {
            cart[id].qty--;
            if (cart[id].qty === 0) {
                delete cart[id];
            }
        } else {
            let cartItem = {
                title: title,
                price: price,
                image: itemImage,
                qty: 1,
                id: id
            };
            cart[id] = cartItem;
        }

        count--;
        sum -= price;

        console.log(cart);

        localStorage.setItem("cart", JSON.stringify(cart));
        updateCart();
        alert("Item removed to the cart");
    }

    function remove(id, summ, qty) {

        delete cart[id];
        count = count - qty;
        sum = sum - summ;
        localStorage.setItem("cart", JSON.stringify(cart));
        updateCart();


    }
    
    


</script>