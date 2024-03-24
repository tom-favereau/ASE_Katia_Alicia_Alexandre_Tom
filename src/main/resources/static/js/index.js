const input = document.querySelector("#search-textarea");
input.addEventListener("keydown", search);

function search(e) {
    if (e.code == 'Enter') {
        window.location.href = `http://localhost:8080/riot/summoner_page/${input.value}`;
    }
}