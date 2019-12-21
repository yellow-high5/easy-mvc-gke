<template>
  <v-container>
    <h1 style="text-align:left">MySQL DB UserTable</h1>
    <v-row>
      <v-col
        cols="12"
        md="6"
      >
        <v-text-field
          v-model="form_query"
          label="Search..."
          prepend-icon="search"
        ></v-text-field>
      </v-col>
      <v-col
        cols="12"
        md="6"
      >
        <v-btn 
          :disabled="selectedUser()"
          color="#41B883"
          class="white--text"
          @click="update" 
          style="margin: 0.5rem;"
        >
          ユーザー更新
        </v-btn>
        <v-btn 
          :disabled="selectedUser()"
          color="#34495E"
          class="white--text"
          @click="remove" 
          style="margin: 0.5rem;"
        >
          ユーザー削除
        </v-btn>
      </v-col>
    </v-row>
    <v-data-table
      v-model="select_user"
      class="transparent"
      loading-text="ユーザーが見つかりません。MySQLとの接続に問題がある可能性があります。"
      :headers="headers"
      :items="users"
      :items-per-page="5"
      :footer-props="{
        itemsPerPageOptions: [5, 10, 20, -1]
      }"
      :search="form_query"
      :single-select="true"
      item-key="name"
      show-select
    >
    </v-data-table>
  </v-container>
</template>

<script>
export default {
  name: 'UserList',
  data: function(){
    return {
      form_query: "",
      select_user: [],
      selectedUser: function() {
        if(this.select_user.length === 0){
          return true
        }
        return false
      },
      headers: [
          {
            text: 'id',
            align: 'left',
            sortable: false,
            value: 'id',
          },
          { text: 'name', value: 'name' },
          { text: 'email', value: 'email' },
      ],
      users: [],
    }
  },
  created: function(){
    fetch("http://localhost:8080/users")
      .then(response =>  {
        return response.json();
      })
      .then((json) => {
        json.map((user) => {
          this.users.push(user)
        })
      })
      .catch((error) => {console.log(error)});
  },
  methods: {
    update: function() {
      var user = this.select_user[0]
      fetch(`http://localhost:8080/users/${user.id}/update/?name=White&email=white@sample.com`, {
        method: "PUT",
        headers:{
          'Content-Type': 'application/json'
        }
      })
      .then(response =>  {
        alert(`${user.id}:${user.name} is updated!`)
        console.log(response);
        this.$router.push('/');
      })
      .catch((error) => {alert(error)});
    },
    remove: function() {
      var user = this.select_user[0]
      alert(`${user.id}:${user.name}を本当に削除しますか？`);
      fetch(`http://localhost:8080/users/${user.id}/delete`, {
        method: "DELETE",
        headers:{
          'Content-Type': 'application/json'
        }
      })
      .then(response =>  {
        alert(`${user.id}:${user.name} is deleted`)
        console.log(response);
        this.$router.push('/');
      })
      .catch((error) => {alert(error)});
    }
  }
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
table {
  width: 100%;
  border: 1px solid #000000;
}
table th,td {
  border: 1px solid #000000;
}
</style>
