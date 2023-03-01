<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
    <div class="sb-sidenav-menu">
        <div class="nav">
            <div class="sb-sidenav-menu-heading">Vynil Shop</div>
            <a class="nav-link" href="NavigationController?to=adminProducts">
                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                Products
            </a>
            <a class="nav-link" href="NavigationController?to=adminUsers">
                <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                Customers
            </a>
            <a class="nav-link" href="NavigationController?to=adminOrders">
                <div class="sb-nav-link-icon"><i class="fas fa-box"></i></div>
                All Orders
            </a>
   

        </div>
    </div>
    <div class="sb-sidenav-footer">
        <div class="small">Logged in as:</div>
        ${sessionScope.adminUser}
    </div>
</nav>