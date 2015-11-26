
var w = window;
function openWin(url) {
if ((w == window) || w.closed) {
w = open(url, "_blank", "width=300,height=300"); 
} else {
w.focus(); 
}
return(false);
}
