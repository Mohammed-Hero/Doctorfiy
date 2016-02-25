$(document).ready(function () {
    $('#sym_list').hide();

    $('#ShoulderR, #ShoulderL').on('click tap', function (event) {

        $('#sym_list li:not(:first)').remove();

        $('#humanbody').attr("src", 'IMG/shoulder-click.png');
        $('#humanbody').attr("style", 'transform: translate3d(100%, 0px, 0px);');
        $('#sym_list').show();
        
        var search = $('#ShoulderR').attr("title");
        $.get('ViewSymptoms',{bodypart: search}, function (responseText) {

            var txtArray = responseText.split("/");
            for(i =0 ; i < 8 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
        });
    });
    $('#ArmR, #ArmL').on('click tap', function (event) {
        
        $('#sym_list li:not(:first)').remove();

        $('#humanbody').attr("src", 'IMG/arm-click.png');
        $('#humanbody').attr("style", 'transform: translate3d(100%, 0px, 0px);');
        $('#sym_list').show();
        
                var search = $('#ArmR').attr("title");
        $.get('ViewSymptoms',{bodypart: search}, function (responseText) {

            var txtArray = responseText.split("/");
            for(i =0 ; i < 8 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
        });

    });
    $('#ElbowR, #ElbowL').on('click tap', function (event) {
        
        $('#sym_list li:not(:first)').remove();

        $('#humanbody').attr("src", 'IMG/elbow-click.png');
        $('#humanbody').attr("style", 'transform: translate3d(100%, 0px, 0px);');
        $('#sym_list').show();
        
                var search = $('#ElbowR').attr("title");
        $.get('ViewSymptoms',{bodypart: search}, function (responseText) {

            var txtArray = responseText.split("/");
            for(i =0 ; i < 8 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
        });

    });
    $('#ForearmR, #ForearmL').on('click tap', function (event) {
        
        $('#sym_list li:not(:first)').remove();

        $('#humanbody').attr("src", 'IMG/forearm-click.png');
        $('#humanbody').attr("style", 'transform: translate3d(100%, 0px, 0px);');
        $('#sym_list').show();
        
                var search = $('#ForearmR').attr("title");
        $.get('ViewSymptoms',{bodypart: search}, function (responseText) {

            var txtArray = responseText.split("/");
            for(i =0 ; i < 8 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
        });

    });
    $('#WristR, #WristL').on('click tap', function (event) {
        
        $('#sym_list li:not(:first)').remove();

        $('#humanbody').attr("src", 'IMG/wrist-click.png');
        $('#humanbody').attr("style", 'transform: translate3d(100%, 0px, 0px);');
        $('#sym_list').show();
        
                var search = $('#WristR').attr("title");
        $.get('ViewSymptoms',{bodypart: search}, function (responseText) {

            var txtArray = responseText.split("/");
            for(i =0 ; i < 8 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
        });

    });
    $('#PalmR, #PalmL').on('click tap', function (event) {
        
        $('#sym_list li:not(:first)').remove();

        $('#humanbody').attr("src", 'IMG/palm-click.png');
        $('#humanbody').attr("style", 'transform: translate3d(100%, 0px, 0px);');
        $('#sym_list').show();
        
                var search = $('#PalmR').attr("title");
        $.get('ViewSymptoms',{bodypart: search}, function (responseText) {

            var txtArray = responseText.split("/");
            for(i =0 ; i < 8 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
        });

    });
    $('#FingersR, #FingersL').on('click tap', function (event) {
        
        $('#sym_list li:not(:first)').remove();

        $('#humanbody').attr("src", 'IMG/fingers-click.png');
        $('#humanbody').attr("style", 'transform: translate3d(100%, 0px, 0px);');
        $('#sym_list').show();
        
                var search = $('#FingersR').attr("title");
        $.get('ViewSymptoms',{bodypart: search}, function (responseText) {

            var txtArray = responseText.split("/");
            for(i =0 ; i < 8 ; i++){
                $('#sym_list').append("<li><input type='checkbox'  class='select' id='"+txtArray[i+1]+"'><a>"+txtArray[i]+"</a></li>");
                i++;
            }
        });

    });
    /////////////////////////
    $('#ShoulderR, #ShoulderL').on('mouseover', function (event) {

        $('#humanbody').attr("src", 'IMG/shoulder-hover.png');

    });
    $('#ArmR, #ArmL').on('mouseover', function (event) {

        $('#humanbody').attr("src", 'IMG/arm-hover.png');

    });
    $('#ElbowR, #ElbowL').on('mouseover', function (event) {

        $('#humanbody').attr("src", 'IMG/elbow-hover.png');

    });
    $('#ForearmR, #ForearmL').on('mouseover', function (event) {

        $('#humanbody').attr("src", 'IMG/forearm-hover.png');

    });
    $('#WristR, #WristL').on('mouseover', function (event) {

        $('#humanbody').attr("src", 'IMG/wrist-hover.png');

    });
    $('#PalmR, #PalmL').on('mouseover', function (event) {

        $('#humanbody').attr("src", 'IMG/palm-hover.png');

    });
    $('#FingersR, #FingersL').on('mouseover', function (event) {

        $('#humanbody').attr("src", 'IMG/fingers-hover.png');

    });
});
