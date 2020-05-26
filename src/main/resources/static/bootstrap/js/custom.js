$(document).ready(function () {
    $('#inputCity').on('change.bs.select', function () {
        getProvince();
        return false;
    });

    $('#inputProvince').on('change.bs.select', function () {

        getCommune();
        return false;
    });
    $('#inputPX').on('change.bs.select', function () {

        getHamlet();
        return false;
    });
});

function getProvince() {
    var url = "/api/province/";
    var lstmatinh = $("#inputCity").val();
    if (lstmatinh !== "") {
        $('#inputProvince option').remove();
        $('#inputPX option').remove();
        $('#inputTX option').remove();
        $('#inputProvince').val('');
        $('#inputPX').val('');
        $('#inputTX').val('');

        $.ajax({
            url : url+lstmatinh,
            success : function (data) {
                var inputElem = $('#inputProvince');
                // inputElem.prop('disabled', false);
                inputElem.append($('<option>', {
                    value: "",
                    text: "-Chọn huyện-"
                }));
                $(data).each(function (i, item) {
                    console.log(item);
                    inputElem.append($('<option>', {
                        value: item["idprovince"],
                        text: item["name"]
                    }));
                });
                $('.selectpicker').selectpicker('refresh');
            }
        });
    } else {
        // $("#inputProvince").prop('disabled', 'disabled');
        // $("#inputPX").prop('disabled', 'disabled');
        // $("#inputTX").prop('disabled', 'disabled');
    }
}

function getCommune() {
    var url = "/api/commune/";
    var id = $("#inputProvince").val();
    $('#inputPX option').remove();
    $('#inputTX option').remove();
    $('#inputPX').val('');
    $('#inputTX').val('');

    if (id !== "") {
        $.ajax({
            url : url+id,
            success : function (data) {

                $('#inputPX').append($('<option>', {
                    value: "",
                    text: "-Chọn Phường/Xã-"
                }));
                $(data).each(function (i, item) {
                    console.log(item);
                    $('#inputPX').append($('<option>', {
                        value: item["idcommune"],
                        text: item["commune"]
                    }));
                });
                $('.selectpicker').selectpicker('refresh');
            }
        });
    }
    else {
        // $("#inputPX").prop('disabled', 'disabled');
        // $("#inputTX").prop('disabled', 'disabled');
    }
}

function getHamlet() {
    var url = "/api/hamlet/";
    var id = $("#inputPX").val();
    $('#inputTX option').remove();
    $('#inputTX').val('');
    if (id !== "") {
        $.ajax({
            url : url+id,
            success : function (data) {
                $('#inputTX').append($('<option>', {
                    value: "",
                    text: "-Chọn Thôn/Xóm-"
                }));
                $(data).each(function (i, item) {
                    console.log(item);
                    $('#inputTX').append($('<option>', {
                        value: item["idhamlet"],
                        text: item["hamlet"]
                    }));
                });
                $('.selectpicker').selectpicker('refresh');
            }
        });

    }
    else {
        // $("#inputTX").prop('disabled', 'disabled');
    }
}