<template>
  <div class="search-item-container">
    <div class="search-controls">
      <input type="text" v-model="searchQuery" placeholder="종목 검색" />
      <button @click="fetchItems">검색</button>
    </div>

    <div class="search-results" v-if="items.length">
      <h2>검색 결과</h2>
      <ul>
        <li v-for="item in items" :key="item.id" @click="goToTrading(item)">
          <span>{{ item.name }}</span>
          <span>{{ item.category }}</span>
          <span>{{ item.price }}원</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: '',
      items: [],
      accountType: '',
    };
  },
  created() {
    this.accountType = this.$store.state.typeAccount;
  },
  methods: {
    fetchItems() {
      // API 호출 로직
      // 여기에 실제 API 호출 코드를 추가합니다. 아래는 예시 데이터입니다.
      this.items = [
        { id: 1, name: '아이템1', category: '카테고리1', price: '1000' },
        { id: 2, name: '아이템2', category: '카테고리2', price: '2000' },
        // 추가 데이터...
      ];
    },
    goToTrading(item) {
      // 'trading' 페이지로 이동하는 로직
      this.$router.push({ name: 'trading', query: { itemId: item.id } });
    }
  }
};
</script>

<style>
body {
  background-color: #000000; /* Black color */
  margin: 0; /* Removes default margin */
  padding: 0; /* Removes default padding */
  min-height: 100vh; /* 100% of the viewport height */
}

.search-item-container {
  padding: 20px;
  background-color: #000000;
  border-radius: 10px;
  margin: 20px;
}

.search-controls {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.search-controls input {
  flex-grow: 1;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.search-controls button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.search-controls button:hover {
  background-color: #0056b3;
}

.search-results h2 {
  margin-bottom: 10px;
  color: white;
}

.search-results ul {
  list-style: none;
  padding: 0;
}

.search-results li {
  background-color: darkslategray;
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.search-results span {
  display: inline-block;
  margin-right: 20px;
}

@media (max-width: 600px) {
  .search-item-container {
    margin: 0 auto;
  }
}
</style>
