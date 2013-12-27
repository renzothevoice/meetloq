//cambia il colore del pulsante menu in base alla pagina(!!!non attivo!!!)
function menuchangecolor(){
var sPath = window.location.pathname;
var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
alert(sPage);
}

//slider
function autoSlide() {
    var nextidx = (parseInt($('#slideshow .current').index() + 1) === $('#slideshow .slide').length) ? 0 : parseInt($('#slideshow .current').index() + 1);
    $('#slideshow .current').fadeOut('slow', function() {
        $(this).removeClass('current');
        $('#slideshow .slide').eq(nextidx).fadeIn('slow', function() {
            $(this).addClass('current');
        });
    });
}
setInterval(autoSlide, 6000);

