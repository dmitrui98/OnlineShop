
$(document).ready(function() {

    document.getElementById('inputImage').onchange = function changeImage(event) {
        var tmppath = URL.createObjectURL(event.target.files[0]);
        $("#image").attr('src',tmppath);
    }

    $('#materialAddButton').on('click', function(e) {
        e.preventDefault();
        $('#composition').removeClass('hide');
        $('#emptyCompositionLabel').addClass('hide');

        var id = $('#selectedMaterial').val();
        var name = $('#selectedMaterial').find('option:selected').text();
        var percent = $('#percent').val();

        var source = $("#table-row").html();
        var template = Handlebars.compile(source);

        var context = {
            id: id,
            name: name,
            percent: percent
        };

        var html = template(context);
        $('#composition').append(html);
    });


    $(document).on('click', '.delete', function(e) {
        e.preventDefault();
        $(this).closest('tr').remove();

        if ($('#composition').find('tr').length == 0) {
            $('#composition').addClass('hide');
            $('#emptyCompositionLabel').removeClass('hide');
        }
    });


});
