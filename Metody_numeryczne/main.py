import pandas as pd
from matplotlib import pyplot as plt

def ema(n, current, samples):
    alfa = 2 / (n + 1)
    a = 1 - alfa
    x = 1                                                           #mianownik
    y = 0                                                           #licznik

    for i in range(1, n):                                           #obliczenie mianownika
        x += a**i
    for i in range(n):                                              #obliczanie licznika
        tmp = a ** i
        if (current - i > 0):
            y += tmp * samples[current - 1 - i]
        else:
            y += tmp * samples[0]
    return y/x

def macd(samples, current):
    return ema(12, current, samples)-ema(26, current, samples)

def signal(macd_values, current):
    return ema(9, current, macd_values)


if __name__ == '__main__':
    n = 1000
    samples = pd.read_csv("yuan.csv")
    samples = samples[::-1]
    macd_values = []
    signal_values = []

    for i in range(0, n):
        macd_values.append(macd(samples.Value, i))

    for i in range(0, n):
        signal_values.append(signal(macd_values, i))

    for value in signal_values:
        print(value)

    plt.plot(samples.Date, macd_values, 'r', linewidth = '2', label = 'MACD')
    plt.plot(samples.Date, signal_values, 'b', linewidth = '1', label = 'SIGNAL')
    plt.plot(samples.Date, samples.Value, 'k', linewidth = '1', label = 'Dane wejściowe')
    #plt.title("Wykres MACD oraz SIGNAL")
    plt.xlabel("Data (yy-mm-dd)")
    plt.ylabel("Kurs")
    plt.legend()
    plt.grid(color='grey', linestyle='--', linewidth=0.5)
    plt.xticks(['2018-09-03', '2019-03-05', '2019-09-02', '2020-03-02', '2020-09-01', '2021-03-01', '2021-09-01', '2022-03-01'])
    plt.show()
