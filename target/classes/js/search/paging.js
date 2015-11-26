$(document).ready(function() {
    var options = {
      currPage : 1,  //最初に表示するページ
      optionsForRows : [1,5,10], //表示する行数
      rowsPerPage : 10 //デフォルト表示行数
    }
    $('#page').tablePagination(options);
});