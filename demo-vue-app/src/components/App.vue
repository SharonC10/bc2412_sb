<template>
  <div id="app" class="bg-gray-900 text-white min-h-screen flex flex-col">
    <!-- Header -->
    <header class="flex justify-between items-center p-4 bg-gray-700 shadow-lg">
      <div class="text-white text-lg font-bold">Sharon Project</div>
      <nav>
        <ul class="flex space-x-4">
          <li><a href="#" class="text-white hover:underline">HOME</a></li>
          <li><a href="#" class="text-white hover:underline">ABOUT US</a></li>
          <li><a href="#" class="text-white hover:underline">PAGES</a></li>
          <li><a href="#" class="text-white hover:underline">BLOG</a></li>
          <li><a href="#" class="text-yellow-500 hover:underline">LOGIN</a></li>
        </ul>
      </nav>
    </header>

    <!-- Main Content -->
    <main class="flex flex-col items-center justify-center flex-grow text-center mt-10">
      <h1 class="text-5xl font-bold mb-4">Best Secure Investment Plan</h1>
      <p class="text-lg mb-8">Join us and start earning today!</p>
      <div class="flex space-x-4">
        <button class="bg-yellow-500 text-gray-900 font-bold py-2 px-4 rounded hover:bg-yellow-600 transition">Get Started</button>
        <button class="border border-yellow-500 text-yellow-500 font-bold py-2 px-4 rounded hover:bg-yellow-500 hover:text-gray-900 transition">Watch Video</button>
      </div>
    </main>

    <!-- Marquee -->
    <div class="marquee w-full h-12 overflow-hidden relative mt-10">
      <ul class="marquee-content h-full flex" ref="mq">
        <li v-for="coin in tenCoins" :key="coin.name" class="flex justify-center items-center flex-shrink-0 text-white transform scale-75 lg:scale-100">
          <div class="flex justify-between w-3/4">
            <div class="flex items-center">
              <img :src="coin.image" alt="coin logo" class="w-6 h-6 rounded-full mr-4 object-cover" />
              <div class="hidden lg:block">
                <p class="font-bold">{{ coin.name }}</p>
                <p class="text-xs uppercase tracking-widest">{{ coin.symbol }}</p>
              </div>
            </div>
            <div>
              <p class="font-bold text-xs lg:text-base flex justify-end">{{ $filters.comma_separator(coin.currentPrice) }}</p>
              <p v-if="$filters.price_negative(coin.priceChangePercentage24h)" class="font-bold text-xs text-red-400 flex justify-end items-center">
                <fa icon="caret-down" class="mr-1" />
                {{ $filters.string_trunc(coin.priceChangePercentage24h, 5) }}%
              </p>
              <p v-else class="font-bold text-xs text-green-400 flex justify-end items-center">
                <fa icon="caret-up" class="mr-1" />
                {{ $filters.string_trunc(coin.priceChangePercentage24h, 5) }}%
              </p>
            </div>
          </div>
        </li>
      </ul>
    </div>

    <!-- Table -->
    <div class="container mx-auto pt-6 px-2">
      <table class="table-fixed cursor-pointer bg-gray-800 rounded-lg shadow-lg">
        <caption class="table-title font-bold text-gray-200 pb-2">Cryptocurrency Exchange - Top 20 (Market Cap)</caption>
        <thead>
          <tr class="relative text-left text-gray-400 text-sm">
            <td class="p-2">
              Search:
              <input type="text" placeholder="Coin Name ..." class="border border-gray-600 rounded p-2 bg-gray-700 text-gray-200" v-model="search" />
            </td>
          </tr>
          <tr class="coin-header bg-gray-700">
            <th class="w-1/4 p-4">Coin Name</th>
            <th class="w-1/4">Market Price(Real Time)</th>
            <th class="w-1/4">Change%(24 Hours)</th>
            <th class="w-1/4 hidden sm:table-cell">Trading Volume</th>
            <th class="w-1/4 hidden sm:table-cell">Market Capitalization</th>
          </tr>
        </thead>

        <tbody class="divide-y">
          <tr v-for="coin in matchedNames" :key="coin.name" class="text-sm hover:bg-gray-600 transition duration-300">
            <td class="p-4 flex items-center">
              <p class="mr-2">{{ coin.marketCapRank }}</p>
              <img :src="coin.image" alt="coin logo" class="w-7 h-7 rounded-full mr-1" />
              <p class="font-bold p-1 mr-1">{{ coin.name }}</p>
              <p class="uppercase text-gray-500 hidden sm:table-cell">{{ coin.symbol }}</p>
            </td>
            <td class="font-bold text-gray-300">${{ $filters.comma_separator(coin.currentPrice) }}</td>
            <td class="font-bold">
              <div v-if="$filters.price_negative(coin.priceChangePercentage24h)" class="text-red-400">
                <fa icon="caret-down" class="mr-1" />{{ coin.priceChangePercentage24h }}%
              </div>
              <div v-else class="text-green-400">
                <fa icon="caret-up" class="mr-1" />{{ coin.priceChangePercentage24h }}%
              </div>
            </td>
            <td class="hidden sm:table-cell text-teal-400">
              {{ $filters.comma_separator(coin.totalVolume) }}
            </td>
            <td class="hidden sm:table-cell text-teal-400 pr-10">
              ${{ $filters.comma_separator(coin.marketCap) }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Footer -->
    <footer class="p-4 bg-gray-700 text-center">
      <p>email@email.com | +852-1234-8888</p>
    </footer>
  </div>
</template>

<script>
import "./assets/tailwind.css";
import { computed, ref, watchEffect } from "vue";
import axios from 'axios';

export default {
  name: "App",
  setup() {
    const coins = ref([]);
    const cloneCoins = ref([]);
    const search = ref('');
//api call
    const retrieveCoins = async () => {
      try {
        const response = await axios.get("http://localhost:8088/crypto/api/v1/cache/coin/market");
        coins.value = response.data;
      } catch (err) {
        console.log(err);
      }
    };

    setInterval(() => {
      retrieveCoins();
    }, 10000); // 60000 (1mins)

    const tenCoins = computed(() => coins.value.slice(0, 10));

    watchEffect(() => {
      cloneCoins.value = coins.value.slice(0, 5);
    });

    const matchedNames = computed(() => {
      return coins.value.filter((coin) => coin.id.includes(search.value));
    });

    return { tenCoins, cloneCoins, matchedNames, search };
  },
};
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.coin-header {
  background-color: #4b5563; /* Darker header */
  color: #e5e7eb; /* Lighter text */
}

.coin-header th {
  font-weight: bold;
  text-align: left;
  border-bottom: 2px solid #9ca3af; /* Light gray border */
}

.marquee-content {
  animation: scrolling 25s linear infinite;
}

.marquee-content li {
  width: 19%;
}

.table-title {
  font-size: 30px;
  color: #e5e7eb; /* Lighter color */
}

@keyframes scrolling {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-100%);
  }
}
</style>