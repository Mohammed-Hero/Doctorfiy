$(document).ready(function () {

    $('#next').on('click tap', function (event) {
        var val = [];
        $('.select:checked').each(function (i) {
            val[i] = $(this).attr('id');
        });
        
        $.get('DiseaseIdentifier',{sym_list: val}, function (responseText) {
            
            
        });
        

    });


});


