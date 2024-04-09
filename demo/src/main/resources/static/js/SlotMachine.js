// Объект с символами и их стоимостями
const symbols = {
    "🍒": { 3: 0.25, 4: 0.75, 5: 2 },
    "🍋": { 3: 0.40, 4: 0.90, 5: 4 },
    "🍊": { 3: 0.50, 4: 1, 5: 5 },
    "🍇": { 3: 0.80, 4: 1.20, 5: 8 },
    "🍉": { 3: 1, 4: 1.5, 5: 10 },
    "🍏": { 3: 1.5, 4: 2, 5: 12 },
    "🍓": { 3: 2, 4: 5, 5: 15 },
    "🍆": { 3: 2.5, 4: 10, 5: 25 },
    "🍬": { 3: 10, 4: 25, 5: 50 }
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
