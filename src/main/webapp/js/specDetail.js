function PrintScript() {
    //印刷範囲を設定
    htmlcode = window.document.body.innerHTML; 
    sprnstr  = "<!--PrintStart-->"; 
    eprnstr  = "<!--PrintEnd-->"; 
    var prnhtml = htmlcode.substr(htmlcode.indexOf(sprnstr)+sprnstr.length); 
    prnhtml = prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
    window.document.body.innerHTML = prnhtml; 
    window.print(); 
    window.document.body.innerHTML = htmlcode;
}