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
        <!--更新-->
        <v-dialog
          v-model="update_dialog"
          width="500"
        >
          <template v-slot:activator="{ on }">
            <v-btn
              :disabled="selectedUser()"
              v-on="on"
              color="#41B883"
              class="white--text" 
              style="margin: 0.5rem;"
            >
              ユーザー更新
            </v-btn>
          </template>

          <v-card>
            <v-form>
            <v-card-title>
              更新情報
            </v-card-title>

            <v-card-text>              
              <v-text-field
                name="name"
                v-model="select_user[0].name"
                :counter="20"
                label="名前"
                append-icon="person"
                required
              ></v-text-field>
              <v-text-field
                name="email"
                v-model="select_user[0].email"
                :counter="50"
                label="メールアドレス"
                append-icon="email"
                required
              ></v-text-field>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="#41B883"
                class="white--text"
                @click="update"
              >
                更新する
              </v-btn>
            </v-card-actions>
            </v-form>
          </v-card>
        </v-dialog>
        <!--削除-->
        <v-dialog
          v-model="delete_dialog"
          width="500"
        >
          <template v-slot:activator="{ on }">
            <v-btn
              :disabled="selectedUser()"
              v-on="on"
              color="#34495E"
              class="white--text"
              style="margin: 0.5rem;"
            >
              ユーザー削除
            </v-btn>
          </template>

          <v-card>
            <v-form>
            <v-card-title>
              削除
            </v-card-title>

            <v-card-text>              
              本当に削除しますか？
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="#EF5350"
                class="white--text"
                @click="remove"
              >
                削除する
              </v-btn>
            </v-card-actions>
            </v-form>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
    <v-data-table
      v-model="select_user"
      class="transparent"
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
console.log(process.env.VUE_APP_API_ORIGIN);
console.log('hello')
export default {
  name: 'UserList',
  data: function(){
    return {
      form_query: "",
      update_dialog: false,
      delete_dialog: false,
      select_user: [{name: null, email: null}],
      selectedUser: function() {
        var user = this.select_user[0]
        if(this.select_user[0] == null || (user.name === null && user.email === null)){
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
    fetch(`${process.env.VUE_APP_API_ORIGIN}/users`)
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
      this.update_dialog = false
      var user = this.select_user[0]
      fetch(`${process.env.VUE_APP_API_ORIGIN}/users/${user.id}/update/?name=${user.name}&email=${user.email}`, {
        method: "PUT",
        headers:{
          'Content-Type': 'application/json'
        }
      })
      .then(response =>  {
        if(response){
          this.$router.go({path: '/', force: true})
        }
        // this.$router.push('/');
      })
      .catch((error) => {alert(error)});
    },
    remove: function() {
      this.delete_dialog = false
      var user = this.select_user[0]
      fetch(`${process.env.VUE_APP_API_ORIGIN}/users/${user.id}/delete`, {
        method: "DELETE",
        headers:{
          'Content-Type': 'application/json'
        }
      })
      .then(response =>  {
        if(response){
          this.$router.go({path: '/', force: true})
        }
        // this.$router.push('/');
      })
      .catch((error) => {alert(error)});
    },
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
