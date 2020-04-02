

function toMain() {
    window.location.href = '/cars';
}


function getModelsById(brand_id) {
    var selectedValue;
    if (brand_id === '0') {
        selectedValue = $('select[name="brands"]').val();
    } else {
        selectedValue = brand_id;
    }

    $.ajax('./createAd', {
        method : 'get',
        data: {
            brandId: selectedValue,
        },
        complete: function(data) {
            if (typeof data === "undefined") {
                return;
            }
            console.log(data.responseText);
            var modelsList = JSON.parse(data.responseText);
            console.log(modelsList);
            var selectModels = $('#models');
            selectModels.find("option").remove();
            $.each(modelsList, function (key, value) {
                var id = value.id;
                var name = value.modelName;
                $("<option>").val(id).text(name).appendTo(selectModels);
                // selectModels.append('<option value=' + id + '>' + name + '</option>');
            });

        }
    });
}

function validateNewAd() {
    console.log($('#models').val());
    console.log(document.getElementById('brands').value);
    console.log($('#caryear').val());
    console.log($('#color').val());


    if (($('#models').val() === '') || ($('#models').val()=== null))  {
        alert('Fill the field <Model>');
        return false;
    }
    if ($('#caryear').val() === '') {
        alert('Fill the field <Car year>');
        return false;
    }
    if ($('#color').val() === '') {
        alert('Fill the field <Color>');
        return false;
    }
    if ($('#price').val() === '') {
        alert('Fill the field <Price>');
        return false;
    }
}
//boxRoles

function showTestRoles() {
    $('#topBar #topBarIn #rolesButtons').empty();
    $('#topBar #topBarIn #rolesButtons').append("<div class='boxRoles'><form action='signin' method='post'>" +
        "<button type='submit' class='buttonRoles'>User</button>" +
        "<input type=\"hidden\" name='login' value='User'>" +
        "<input type=\"hidden\" name='password' value='User'></form>" +

        "<form action='signin' method='post'>" +
        "<button type='submit' class='buttonRoles'>Admin</button>" +
        "<input type=\"hidden\" name='login' id=\"login\" value='Admin'>" +
        "<input type=\"hidden\" name='password' id=\"password\" value='Admin'></form></div>"
        );
}


function loadOnlyUserAds() {
    var onlyDoneTasks;
    if ($('#chbUserAds').is(":checked")) {
        onlyDoneTasks = 'y';
    } else {
        onlyDoneTasks = 'n';
    }
    console.log("Check State is " + onlyDoneTasks);
    $.ajax('./items', {
        method : 'get',
        data: {
            onlyDone: onlyDoneTasks,
        },
        complete: function(data) {
            if (typeof data === "undefined") {
                return;
            }
            var items = JSON.parse(data.responseText);
            console.log(items);
            $("#currentItems tbody").empty();
            $.each(items, function (key, value) {
                var akk = "";
                var rowId = value.id;
                var currentState = value.done;
                console.log(rowId, currentState);
                $.each(value, function (key, value) {
                    if ((key === 'created')) {
                        akk += '<td>' + new Date(value).toLocaleString() + '</td>';
                    } else {
                        akk += '<td>' + value + '</td>';
                    }
                });
                akk += '<td><input type="checkbox" onclick="changeState(' + rowId;
                akk += ',' + currentState + ')"/></td>';
                console.log(akk);
                $('#currentItems tbody').append('<tr>' + akk + '</tr>');
            });

        }
    });
}
