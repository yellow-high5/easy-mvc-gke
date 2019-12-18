<template>
  <div>
    <h1>MySQL DB Post Form</h1>
    <label for="name">名前 : </label>
    <input type="name" name="name" v-model="form_name"/>
    <label for="email">メールアドレス : </label>
    <input type="email" name="email" v-model="form_email"/>
    <button class="button" v-on:click="post">ユーザー作成</button>
  </div>
</template>

<script>
export default {
  name: 'UserForm',
  data: function(){
    return {
      form_name: "",
      form_email: "",
    }
  },
  methods: {
    post: function(){
      fetch(`http://localhost:8080/users/new/?name=${this.form_name}&email=${this.form_email}`, {
        method: "POST",
        headers:{
          'Content-Type': 'application/json'
        }
      })
      .then(response =>  {
        alert('Success!')
        console.log(response);
        this.$router.push('/list');
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
</style>
