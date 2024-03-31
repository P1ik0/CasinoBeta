// Объект с символами и их стоимостями
const symbols = {
    "🍒": { 8: 0.25, 9: 0.75, 10: 2 },
    "🍋": { 8: 0.40, 9: 0.90, 10: 4 },
    "🍊": { 8: 0.50, 9: 1, 10: 5 },
    "🍇": { 8: 0.80, 9: 1.20, 10: 8 },
    "🍉": { 8: 1, 9: 1.5, 10: 10 },
    "🍏": { 8: 1.5, 9: 2, 10: 12 },
    "🍓": { 8: 2, 9: 5, 10: 15 },
    "🍆": { 8: 2.5, 9: 10, 10: 25 },
    "🍬": { 8: 10, 9: 25, 10: 50 }
};

// Функция для случайного выбора символа
function getRandomSymbol() {
    const symbolsKeys = Object.keys(symbols);
    return symbolsKeys[Math.floor(Math.random() * symbolsKeys.length)];
}

// Функция для проверки и замены выигрышных комбинаций
function checkAndReplaceWinningCombination(slots) {
    let totalWinnings = 0;

    // Собираем символы с реелов в массив
    const symbolsOnReels = Array.from(slots, slot => slot.textContent);
    // Создаём объект для подсчёта количества каждого символа
    let symbolsCount = symbolsOnReels.reduce((acc, symbol) => {
        acc[symbol] = (acc[symbol] || 0) + 1;
        return acc;
    }, {});

    // Проверяем каждый символ на выигрышную комбинацию
    for (let symbol in symbolsCount) {
        let count = symbolsCount[symbol];
        if (symbols[symbol] && symbols[symbol][count]) {
            totalWinnings += symbols[symbol][count];
            // Заменяем выигрышные символы новыми
            for (let i = 0; i < slots.length; i++) {
                if (slots[i].textContent === symbol) {
                    slots[i].textContent = getRandomSymbol();
                }
            }
        }
    }

    return totalWinnings;
}

// Функция для обновления баланса и результатов игры
function updateBalanceAndResult(winnings, winningSymbol, count) {
    const balanceElement = document.getElementById('balance');
    let balance = parseInt(balanceElement.innerText);
    const resultElement = document.getElementById('result');
    const roundInfoElement = document.getElementById('roundInfo');

    // Добавляем выигрыш к балансу
    balance += winnings;

    // Отображаем выигрыш или сообщение о проигрыше
    if (winnings > 0) {
        resultElement.innerText = `Вы выиграли ${winnings} баллов! Символ: ${winningSymbol} (${count} шт)`;
    } else {
        resultElement.innerText = "Вы не выиграли ничего. Попробуйте еще раз!";
    }

    // Обновляем баланс
    balanceElement.innerText = balance;

    // Обновляем информацию о текущем круге
    const currentRound = parseInt(roundInfoElement.innerText.split(':')[1].trim()) + 1;
    roundInfoElement.innerText = `Текущий круг: ${currentRound}. Выигрыш: ${winnings}`;
}

// Получаем доступ к кнопке "Spin" и к барабанам слот-машины
const spinBtn = document.getElementById('spinBtn');
const slots = document.querySelectorAll('.slot');

// Функция для выполнения спина
function spin() {
    const balanceElement = document.getElementById('balance');
    let balance = parseInt(balanceElement.innerText);
    const spinCost = 1; // Стоимость спина

    if (balance >= spinCost) {
        balance -= spinCost;
        balanceElement.innerText = balance;

        // Заменяем символы на реелах
        slots.forEach(slot => slot.textContent = getRandomSymbol());

        // Проверяем и заменяем выигрышные комбинации, а также обновляем выигрыш
        let winnings = checkAndReplaceWinningCombination(slots);
        if (winnings > 0) {
            updateBalanceAndResult(winnings); // Обновляем баланс и результаты игры
        } else {
            updateBalanceAndResult(0); // Обновляем результаты игры без выигрыша
        }
    } else {
        // Обработка случая недостаточного баланса
        alert("Недостаточно средств для спина!");
    }
}

// Привязка функции spin к кнопке
spinBtn.addEventListener('click', spin);
