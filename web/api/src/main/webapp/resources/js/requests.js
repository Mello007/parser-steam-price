
var currency = '';
$(document).ready(function() {
    $('.dropdown-menu-kind li a').click(function(){
        currency = $(this).data('val');
    })
});
function addNewItem() {
    var name = $('#itemName').val();
    var requestJSONparametr = "{\"itemName\": \"" + name + "\", \"itemKind\": \"" + currency + "\"}";
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


function updatePrice() {
    var priceRequest = new XMLHttpRequest();
    priceRequest.open("GET", "/item/all", true);   //Указываем адрес GET-запроса
    priceRequest.onload = function (){             //Функция которая отправляет запрос на сервер для получения всех студентов
        var parsedItem = JSON.parse(this.responseText);
        var itemsTable = document.getElementById('all-items'); //получаем элемент по Id
        itemsTable.innerHTML = '';      //очищаем таблицу от устаревших данных
            parsedItem.forEach(function(item)  {
            var itemNameElement = document.createElement('td'); //создаем элемент ячейку с названием для таблицы
            itemNameElement.innerHTML =  item['itemName'] ;     //внедряем название предмета, полученное с сервера
            var itemPriceElement = document.createElement('td');
            itemPriceElement.innerHTML = item['itemPrice'];     //создаем элемент ячейку с ценой для таблицы

            var elementRow = document.createElement('tr'); //создаем строку таблицы
            elementRow.appendChild(itemNameElement);      //помещаем обе ячейки в строку
            elementRow.appendChild(itemPriceElement);
            itemsTable.appendChild(elementRow);           //помещаем строку в таблицу
        });
    };
    priceRequest.send(null);
}

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
    updatePrice();
    setInterval(updatePrice,5000);
});