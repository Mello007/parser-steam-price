function login() {
    var email = $('#inputEmail3').val();
    var password = $('#inputPassword3').val();
    var requestJSONparametr = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
    $.ajax({
        type: "POST",
        url: "/teacher/delete",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr,
        success: function (data) {
            alert("Преподаватель успешно удален!");
        },
        error: function (data) {
            alert("Не удалось удалить преподавателя! Возможно, вы ввели неправильно имя либо ввели недопустимое значение имени");
        }
    });
}

var globalvar = '';
$(document).ready(function() {
    $('.dropdown-menu-kind li a').click(function(){
        globalvar = $(this).data('val');
    })
});
function addNewItem() {
    var name = $('#itemName').val();
    var requestJSONparametr = "{\"itemName\": \"" + name + "\", \"itemKind\": \"" + globalvar + "\"}";
    $.ajax({
        type: "POST",
        url: "/item/add",
        contentType: "application/json",
        dataType: 'json',
        data: requestJSONparametr,
        success: function (data) {
            alert("Предмет успешно добавлен!");
        },
        error: function (data) {
            alert("Не удалось добавить предмет! Что-то пошло не так, попробуйте еще раз");
        }
    });
}


var x = new XMLHttpRequest();
x.open("GET", "/item/all", true);  //Указываем адрес GET-запроса
x.onload = function (){ //Функция которая отправляет запрос на сервер для получения всех студентов
    var parsedItem = JSON.parse(this.responseText); //указываем что
    var studentTable = document.getElementById('all-items'); //получаем данные на странице по Id  - all-student
    parsedItem.forEach(function(item)  { //запускаем цикл
        var fullNameElement = document.createElement('td'); //создаем элемент td для таблицы
        fullNameElement.innerHTML =  item['itemName'] ; //внедряем имя студента из БД
        var estimateElement = document.createElement('td');
        estimateElement.innerHTML = item['itemPrice'];//создаем элемент td для таблицы
        var elementContainer = document.createElement('tr'); //создаем тег
        elementContainer.appendChild(fullNameElement);
        elementContainer.appendChild(estimateElement);
        studentTable.appendChild(elementContainer);
    });
    //подключаем к таблице библиотеку для сортировки
};
x.send(null);


setInterval(x, 50000);


$(document).ready(function() {
    $('.dropdown-menu li a').click(function(){
        var val_cur = $(this).data('val');
        var requestJSONparametr = "{\"itemCurr\": \"" + val_cur + "\"}";
        $.ajax({
            type: "POST",
            url: "/item/curr",
            contentType: "application/json",
            dataType: 'json',
            data: requestJSONparametr,
            success: function (data) {
                alert("Цена установлена");
            },
            error: function (data) {
                alert("Не удалось установить цену!");
            }
        });
    });
});