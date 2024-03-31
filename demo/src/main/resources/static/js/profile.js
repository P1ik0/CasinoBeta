function deposit() {
    var amount = document.getElementById("deposit-amount").value;
    var data = { "amount": parseFloat(amount) };

    fetch('/api/accounts/1/deposit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            // Добавьте код для обновления баланса на странице
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
    function withdraw() {
        // Ваш код для обработки вывода средств
        console.log("Вызвана функция для снятия средств");
    }

}
