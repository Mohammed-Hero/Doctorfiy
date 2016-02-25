$(document).ready(function () {

    $('#next').on('click tap', function (event) {
        var val = [];
        $('.select:checked').each(function (i) {
            val[i] = $(this).attr('id');
        });

        $.get('DiseaseIdentifier', {sym_list: val}, function (responseText) {

            $.get('ViewSymptoms', function (responseText) {
            $('#selectlist p').remove();
                var txtArray = responseText.split("/");
                for (i = 0; i < txtArray.length - 1; i++) {
                    $('#selectlist').append("<p class='symptoms_select'>" + txtArray[i] + "</p>");

                }
            });

            if (responseText === "1") {
                $.get('SymptomsRecommender', function (responseText) {
                    $('#sym_list li:not(:first)').remove();
                    var txtArray = responseText.split("/");
                    for (i = 0; i < txtArray.length - 1; i++) {
                        $('#sym_list').append("<li><input type='checkbox'  class='select' id='" + txtArray[i + 1] + "'><a>" + txtArray[i] + "</a></li>");
                        i++;
                    }
                });
            }
            else if (responseText === "0") {
                $('#sym_list').hide();
                $('#DiseaseName').attr('style',"color:red");
                $('#DiseaseName').text("Unknown disease");
            }
            else {
                $('#sym_list').hide();
                $('#DiseaseName').html("You have <br> " + responseText);
                
                $.get('DiseaseAttention', function (responseText) {
                    
                    alert(responseText);
                });
            }

        });


    });


});


