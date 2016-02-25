$(document).ready(function () {

    $('#next').on('click tap', function (event) {
        var val = [];
        $('.select:checked').each(function (i) {
            val[i] = $(this).attr('id');
        });
        
        $.get('DiseaseIdentifier',{sym_list: val}, function (responseText) {
  
        });
        $.get('SymptomsRecommender', function (responseText) {
        $('#sym_list li:not(:first)').remove();
        var txtArray = responseText.split("/");
            for(i =0 ; i < txtArray.length-1 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
  
        });
        

    });


});


