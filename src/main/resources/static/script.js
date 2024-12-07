const recipes = [
    {
        title: "Pastel de Pistacho",
        description: "Este pastel se volvió viral gracias a su rico sabor a pistacho.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Bistec de Verano con Maíz",
        description: "Un plato perfecto para las noches de verano.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Guisantes Asados con Limón",
        description: "Un aperitivo ligero y lleno de sabor.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Ensalada de Quinoa",
        description: "Una ensalada fresca y nutritiva con quinoa, verduras y un aderezo ligero.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Sopa de Tomate",
        description: "Una sopa cremosa y reconfortante, perfecta para cualquier ocasión.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Tacos de Pescado",
        description: "Deliciosos tacos de pescado con repollo fresco y salsa de aguacate.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Galletas de Chocolate",
        description: "Galletas suaves y masticables cargadas de trozos de chocolate.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Pasta Alfredo",
        description: "Pasta cubierta con una salsa cremosa de queso parmesano y mantequilla.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Pollo al Limón",
        description: "Pollo marinado en limón y hierbas, luego asado hasta que esté dorado.",
        img: "https://placehold.co/300x200"
    },
    {
        title: "Brownies de Chocolate",
        description: "Brownies ricos y fudgy, perfectos para los amantes del chocolate.",
        img: "https://placehold.co/300x200"
    }
];

const searchInput = document.getElementById('searchInput');
const recipeList = document.getElementById('recipeList');

search("")

searchInput.addEventListener('input', function() {
    search(this.value)
});

function search(value) {
    const searchValue = value.toLowerCase();
    recipeList.innerHTML = '';

    const filteredRecipes = recipes.filter(recipe => 
        recipe.title.toLowerCase().includes(searchValue) ||
        recipe.description.toLowerCase().includes(searchValue)
    );

    filteredRecipes.forEach(recipe => {
        const recipeCard = document.createElement('div');
        recipeCard.classList.add('col-md-4');
        recipeCard.innerHTML = `
            <div class="card">
                <img src="${recipe.img}" class="card-img-top" alt="${recipe.title}">
                <div class="card-body">
                    <h5 class="card-title">${recipe.title}</h5>
                    <p class="card-text">${recipe.description}</p>
                </div>
                <div class="card-footer text-center">
                    <a href="/detalle-receta" class="btn btn-primary">Ver Receta</a>
                </div>
            </div>
        `;
        recipeList.appendChild(recipeCard);
    });

    if (filteredRecipes.length === 0) {
        recipeList.innerHTML = '<p class="text-center">No se encontraron recetas.</p>';
    }
}