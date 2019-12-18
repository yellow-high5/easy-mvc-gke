<template>
  <div>
    <h1>MySQL DB Content</h1>
     <table style="width:100%; border:'1px solid #000'">
      <tr>
        <th>名前</th>
        <th>メールアドレス</th>
      </tr>
      <tr v-for="user in users" v-bind:key="user.id">
        <td>{{ user.name }}</td>
        <td>{{ user.email }}</td>
      </tr>
    </table> 
  </div>
</template>

<script>
export default {
  name: 'UserList',
  data: function(){
    return {
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
      .catch((error) => {alert(error)});
  },
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
