package lab4_5;


import testingfiles.io.DirList;

import java.util.List;


public class FactorLib {

    public static void factorize (long n, List<Long> result) {
        if (n == 0L) {
            return;
        }
        result.add(1L);
        if (n == 1L) {
            return;
        }
        long upperBound = Math.round(Math.sqrt(n));
        for (long divider = 2; divider <= upperBound; divider++) {
            while (n % divider == 0) {
                n /= divider;
                result.add(divider);
            }
        }
        result.add(n);


    }
}

//source: https://github.com/luisfcofv/competitive-programming-book/blob/master/ch9/Pollardsrho.java

  /*  public static long mulmod(long a, long b, long c) { // returns (a * b) % c, and minimize overflow
        long x = 0, y = a % c;
        while (b > 0) {
            if (b % 2 == 1) x = (x + y) % c;
            y = (y * 2) % c;
            b /= 2;
        }
        return x % c;
    }

    //source:https://github.com/charles-wangkai/hackerrank/blob/master/littlepandapower/Solution.java

    static int powmod(int base, int exponent, int modulus) {
        int result = 1;
        while (exponent != 0) {
            if ((exponent & 1) != 0) {
                result = (int)mulmod(result, base, modulus);
            }

            base = (int)mulmod(base, base, modulus);
            exponent >>= 1;
        }
        return result;
    }

    /******************************************************************************
     *  Compilation:  javac BinaryGCD.java
     *  Execution:    java BinaryGCD p q
     *
     *  Reads two commandline parameters p and q and computes the greatest
     *  common divisor of p and q using the binary gcd algorithm.
     *
     ******************************************************************************/

   /* public static int gcd(int p, int q) {
        if (q == 0) return p;
        if (p == 0) return q;

        // p and q even
        if ((p & 1) == 0 && (q & 1) == 0) return gcd(p >> 1, q >> 1) << 1;

            // p is even, q is odd
        else if ((p & 1) == 0) return gcd(p >> 1, q);

            // p is odd, q is even
        else if ((q & 1) == 0) return gcd(p, q >> 1);

            // p and q odd, p >= q
        else if (p >= q) return gcd((p-q) >> 1, q);

            // p and q odd, p < q
        else return gcd(p, (q-p) >> 1);
    }

    /*
    *Все алгоритмы факторизации взяты с http://e-maxx.ru/algo/factorization
     */

   /* int pollard_p_1 (int n) {
        // параметры алгоритма, существенно влияют на производительность и качество поиска
	final int b = 13;
	final int q[] = { 2, 3, 5, 7, 11, 13 };

        // несколько попыток алгоритма
        int a = 5 % n;
        for (int j=0; j<10; j++)
        {

            // ищем такое a, которое взаимно просто с n
            while (gcd (a, n) != 1)
            {
                mulmod (a, a, n);
                a += 3;
                a %= n;
            }

            // вычисляем a^M
            for (int i = 0; i < q.length; i++)
            {
                int qq = q[i];
                int e = (int)  Math.floor(Math.log((double)b) / Math.log((double)qq));
                int aa = powmod (a, powmod (qq, e, n), n);
                if (aa == 0)
                    continue;

                // проверяем, не найден ли ответ
                int g = gcd (aa-1, n);
                if (1 < g && g < n)
                    return g;
            }

        }

        // если ничего не нашли
        return 1;

    }

    int pollard_monte_carlo (int n) {
        int m = 100;
        int b = (int) (Math.random()*32000) % (m-2) + 2;

        Vector<Integer> primes = new Vector<>();
        int m_max = 0;
        if (primes.isEmpty())
            primes.add (3);
        if (m_max < m)
        {
            m_max = m;
            for (int prime = 5; prime<=m; prime+=2)
            {
                boolean is_prime = true;
                for (Integer iter: primes)
                {
                    int div = iter;
                    if (div*div > prime)
                        break;
                    if (prime % div == 0)
                    {
                        is_prime = false;
                        break;
                    }
                }
                if (is_prime)
                    primes.add (prime);
            }
        }

        int g = 1;
        for (int i=0; i<primes.size() && g==1; i++)
        {
            int cur = primes.elementAt(i);
            while (cur <= n)
                cur *= primes.elementAt(i);
            cur /= primes.elementAt(i);
            b = powmod (b, cur, n);
            g = gcd (Math.abs(b-1), n);
            if (g == n)
                g = 1;
        }

        return g;
    }

    public static void factorize (const T & n, std::map<T,unsigned> & result, T2 unused)
    {
        if (n == 1)
            ;
        else
            // проверяем, не простое ли число
            if (isprime (n))
                ++result[n];
            else
                // если число достаточно маленькое, то его разлагаем простым перебором
                if (n < 1000*1000)
                {
                    T div = prime_div_trivial (n, 1000);
                    ++result[div];
                    factorize (n / div, result, unused);
                }
                else
                {
                    // число большое, запускаем на нем алгоритмы факторизации
                    T div;
                    // сначала идут быстрые алгоритмы Полларда
                    div = pollard_monte_carlo (n);
                    if (div == 1)
                        div = pollard_rho (n);
                    if (div == 1)
                        div = pollard_p_1 (n);
                    if (div == 1)
                        div = pollard_bent (n);
                    // придётся запускать 100%-ый алгоритм Ферма
                    if (div == 1)
                        div = ferma (n, unused);
                    // рекурсивно обрабатываем найденные множители
                    factorize (div, result, unused);
                    factorize (n / div, result, unused);
                }
    }*/