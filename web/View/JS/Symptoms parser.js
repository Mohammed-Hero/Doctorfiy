$(document).ready(function () {

    $('#next').on('click tap', function (event) {
        var val = [];
        $('.select:checked').each(function (i) {
            val[i] = $(this).attr('id');
        });

        $.get('DiseaseIdentifier', {sym_list: val}, function (responseText) {
            
            if(responseText === "1"){
            $.get('SymptomsRecommender', function (responseText) {
                $('#sym_list li:not(:first)').remove();
                var txtArray = responseText.split("/");
                for (i = 0; i < txtArray.length - 1; i++) {
                    $('#sym_list').append("<li><input type='checkbox'  class='select' id='" + txtArray[i + 1] + "'><a>" + txtArray[i] + "</a></li>");
                    i++;
                }
                });
            }
            else if(responseText === "0"){
                $('#sym_list').hide();
                $('#DiseaseName').text("Sorry we can't identify your disease");
            }
            else{
                $('#sym_list').hide();
                $('#DiseaseName').text("You have ...."+responseText);
            }

        });



    });


});


