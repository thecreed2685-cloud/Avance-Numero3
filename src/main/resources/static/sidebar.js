document.addEventListener("DOMContentLoaded", () => {
    // 1. Obtener el usuario de la sesión
    const usuarioJSON = sessionStorage.getItem("usuarioLogueado");

    if (!usuarioJSON) {
        // Si no hay sesión, no hace nada (o redirige al login)
        console.warn("No se encontró usuario en sesión para la barra lateral.");
        // Opcional: si estás en una página protegida, redirige
        // if (window.location.pathname !== "/index.html") {
        //    window.location.href = "index.html";
        // }
        return;
    }

    const usuario = JSON.parse(usuarioJSON);

    // 2. Definir los menús
    const menuSupervisor = `
        <a class="section" href="portal-admin.html">
            <ion-icon name="bar-chart-outline"></ion-icon>
            Estadísticas
        </a>
        <a class="section" href="agregar-misiones.html">
            <ion-icon name="add-circle-outline"></ion-icon>
            Agregar Misiones
        </a>
        <a class="section" href="agregar-empleado.html">
            <ion-icon name="person-add-outline"></ion-icon>
            Agregar Empleado
        </a>
    `;

    const menuEmpleado = `
        <a class="section" href="dashboard.html">
            <ion-icon name="stats-chart-outline"></ion-icon>
            Dashboard
        </a>
        <a class="section" href="misiones.html">
            <ion-icon name="speedometer-outline"></ion-icon>
            Misiones
        </a>
        <a class="section" href="reportes.html">
            <ion-icon name="reader-outline"></ion-icon>
            Reportes
        </a>
    `;

    // 3. Seleccionar los contenedores en el HTML
    // Usaremos los IDs que pondremos en el HTML estático
    const userLink = document.getElementById("sidebar-user-link");
    const avatarContainer = document.getElementById("sidebar-avatar");
    const userNameSpan = document.getElementById("sidebar-user-name");
    const navLinksContainer = document.getElementById("sidebar-nav-links");
    const logoutButton = document.getElementById("logout-button");

    if (!avatarContainer || !userNameSpan || !navLinksContainer || !userLink) {
        // No estamos en una página con la barra lateral estándar
        return;
    }

    // 4. Construir el HTML dinámico

    // --- (A) Contenedor de Usuario (Avatar, Nombre y Rol) ---
    const userRole = usuario.puesto || (usuario.esSupervisor ? "Supervisor" : "Empleado");
    const userAvatarUrl = usuario.fotoUrl || 'https://images.icon-icons.com/2483/PNG/512/user_icon_149851.png'; // Fallback
    const nombreCompleto = `${usuario.nombre} ${usuario.apellido}`; // <-- CORRECCIÓN

    // Limpia el contenido estático
    avatarContainer.innerHTML = "";
    userNameSpan.innerHTML = "";

    // Crea y añade la imagen
    const img = document.createElement("img");
    img.src = userAvatarUrl;
    img.alt = "Foto de perfil";
    img.className = "sidebar-avatar-img"; // (Estilizaremos esto en estructure.css)
    avatarContainer.appendChild(img);

    // Añade el nombre y apellido (CORREGIDO)
    userNameSpan.textContent = nombreCompleto;

    // Crea y añade el rol (NUEVO)
    const roleP = document.createElement("p");
    roleP.textContent = userRole;
    roleP.className = "sidebar-user-role"; // (Estilizaremos esto)
    userLink.appendChild(roleP); // Lo añade al final del <a>

    // --- (B) Enlaces de Navegación ---
    navLinksContainer.innerHTML = usuario.esSupervisor ? menuSupervisor : menuEmpleado;

    // --- (C) Botón de Logout ---
    // Añade el listener para limpiar la sesión
    if (logoutButton) {
        logoutButton.addEventListener("click", () => {
            sessionStorage.clear();
        });
    }
});