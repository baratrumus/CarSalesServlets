
/*
    $(function(){
        $('#fillUpdate').on('submit', function(event){
            event.preventDefault();
            event.stopPropagation();
            fillUpdateform($('#fillUpdate input[id="idd"]').val())
        });
    });
*/

$(loadItems());

function loadItems() {
    var onlyDoneTasks;
    if ($('#chbDone').is(":checked")) {
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


function addTask() {
    var description = $('#description');

    $.ajax('./items', {
        method : 'post',
        data: description,
        cache: false,
        complete: loadItems()
    });
}


function changeState(rowId, state) {
    var stateData = { itemId : rowId,
        chState : state }

    $.ajax('./state', {
        method : 'post',
        data: stateData,
        complete: loadItems()
    });
}


function fillUpdateform(userId) {
    $.ajax({
        url: "./update",
        type: 'GET',
        data: {
            id: userId,
        },
        success: function (responseJSON) {
            console.log(JSON.stringify(responseJSON));
            //console.log(responseJSON.City);

            var user = responseJSON.User;
            var sessionRoleName = $('#sessionRoleName').val();
            console.log("Session Role name: " + sessionRoleName);

            $('#idInput').prop("value", user.id);
            $('#idReal').val(user.id);
            $('#nameInput').val(user.name);
            $('#loginInput').val(user.login);
            $('#passInput').val(user.password);
            $('#emailInput').val(user.email);
            $('#CountryInput').val(user.country);

            //to prevent duplicate options when button is pressed again
            var $selectCity = $("#CityInput");
            $selectCity.find("option").remove();

            $.each(responseJSON.City, function(key, value) {
                if (value === user.city ) {
                    $("<option selected>").val(value).text(value).appendTo($selectCity);
                } else {
                    $("<option>").val(value).text(value).appendTo($selectCity);
                }
            });

            if (sessionRoleName === "admin") {
                var $selectRole = $("#roleInput");
                $selectRole.find("option").remove();

                $.each(responseJSON.Roles, function (key, value) {
                    console.log("Admin enter");
                    if (value === user.role.role) {
                        $("<option selected>").val(key).text(value).appendTo($selectRole);
                    } else {
                        $("<option>").val(key).text(value).appendTo($selectRole);
                    }
                });
            } else {
                $.each(responseJSON.Roles, function (key, value) {
                    console.log("Edit user role: " + user.role.role);
                    console.log(value);
                    if (value === user.role.role) {
                        var edt =  $("#editForm").append('<input type="hidden" name="roleInput" id="roleInput">');
                        console.log(edt);
                        $("#roleInput").prop("value", key);
                    }
                });
            }
        }
    });
}


function validate() {
    var name = $('#nameInput').val();
    var login = $('#loginInput').val();
    var pass = document.getElementById('passInput').value;
    if (name === '') {
        alert('Fill the field <Name>');
    }
    if (login === '') {
        alert('Fill the field <Login>');
    }
    if (pass == '') {
        alert('Fill the field <Password>');
    }
    if (pass.length < 4) {
        alert('Password must have 3 simbols at least');
    }
    return true;
}
