var rating = 3.2; //TODO: get rating

const STYLE_NORMAL_STAR = "";
const STYLE_RED_STAR = "invert(12%) sepia(94%) saturate(6754%) hue-rotate(0deg) brightness(97%) contrast(112%)";
const STYLE_LIGHT_GREY_STAR = "invert(76%) sepia(4%) saturate(61%) hue-rotate(314deg) brightness(94%) contrast(78%)";
const STYLE_DARK_GREY_STAR = "invert(12%) sepia(94%) saturate(0%) hue-rotate(0deg) brightness(40%) contrast(112%)";

show_rating(rating);

document.body.addEventListener('mouseover', e => {
    let star = e.target.closest('.star');
    if (!star) { return; }
    switch (star.id) {
        case "star1" :
            document.querySelector("#star1").style.filter = STYLE_RED_STAR;
            document.querySelector("#star2").style.filter = STYLE_LIGHT_GREY_STAR;
            document.querySelector("#star3").style.filter = STYLE_LIGHT_GREY_STAR;
            document.querySelector("#star4").style.filter = STYLE_LIGHT_GREY_STAR;
            document.querySelector("#star5").style.filter = STYLE_LIGHT_GREY_STAR;
            break;

        case "star2" :
            document.querySelector("#star1").style.filter = STYLE_RED_STAR;
            document.querySelector("#star2").style.filter = STYLE_RED_STAR;
            document.querySelector("#star3").style.filter = STYLE_LIGHT_GREY_STAR;
            document.querySelector("#star4").style.filter = STYLE_LIGHT_GREY_STAR;
            document.querySelector("#star5").style.filter = STYLE_LIGHT_GREY_STAR;
            break;

        case "star3" :
            document.querySelector("#star1").style.filter = STYLE_RED_STAR;
            document.querySelector("#star2").style.filter = STYLE_RED_STAR;
            document.querySelector("#star3").style.filter = STYLE_RED_STAR;
            document.querySelector("#star4").style.filter = STYLE_LIGHT_GREY_STAR;
            document.querySelector("#star5").style.filter = STYLE_LIGHT_GREY_STAR;
            break;

        case "star4" :
            document.querySelector("#star1").style.filter = STYLE_RED_STAR;
            document.querySelector("#star2").style.filter = STYLE_RED_STAR;
            document.querySelector("#star3").style.filter = STYLE_RED_STAR;
            document.querySelector("#star4").style.filter = STYLE_RED_STAR;
            document.querySelector("#star5").style.filter = STYLE_LIGHT_GREY_STAR;
            break;

        case "star5" :
            document.querySelector("#star1").style.filter = STYLE_RED_STAR;
            document.querySelector("#star2").style.filter = STYLE_RED_STAR;
            document.querySelector("#star3").style.filter = STYLE_RED_STAR;
            document.querySelector("#star4").style.filter = STYLE_RED_STAR;
            document.querySelector("#star5").style.filter = STYLE_RED_STAR;
            break;

        default :
            break;
    }
});

document.body.addEventListener('mouseout', e => {
    let stars = e.target.closest('#rating');
    if (!stars) { return; }
    show_rating(rating);
});

function show_rating(score) {
    switch (Math.round(score)) {
        case 1 :
            document.querySelector("#star1").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star2").style.filter = STYLE_DARK_GREY_STAR;
            document.querySelector("#star3").style.filter = STYLE_DARK_GREY_STAR;
            document.querySelector("#star4").style.filter = STYLE_DARK_GREY_STAR;
            document.querySelector("#star5").style.filter = STYLE_DARK_GREY_STAR;
            break;
        case 2 :
            document.querySelector("#star1").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star2").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star3").style.filter = STYLE_DARK_GREY_STAR;
            document.querySelector("#star4").style.filter = STYLE_DARK_GREY_STAR;
            document.querySelector("#star5").style.filter = STYLE_DARK_GREY_STAR;
            break;
        case 3 :
            document.querySelector("#star1").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star2").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star3").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star4").style.filter = STYLE_DARK_GREY_STAR;
            document.querySelector("#star5").style.filter = STYLE_DARK_GREY_STAR;
            break;
        case 4 :
            document.querySelector("#star1").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star2").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star3").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star4").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star5").style.filter = STYLE_DARK_GREY_STAR;
            break;
        case 5 :
            document.querySelector("#star1").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star2").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star3").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star4").style.filter = STYLE_NORMAL_STAR;
            document.querySelector("#star5").style.filter = STYLE_NORMAL_STAR;
        default :
            break;
    }
}

function add_rating(score) {
    console.log(score); //TODO: post rating
    show_rating(rating);
}