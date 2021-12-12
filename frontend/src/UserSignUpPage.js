import React, {Component} from 'react';
import axios from "axios";

class UserSignUpPage extends Component {
    state = {
        username: "",
        displayName: "",
        password: "",
        agreed: false
    }

    render() {
        return (<div>
            <h1>Sign Up</h1>
            <form>
                <div>
                    <label htmlFor="username">Username</label>
                    <input type="text" id={'username'} name={'username'} onChange={this.onChangeUsername}/>
                </div>
                <div>
                    <label htmlFor="displayName">Display Name</label>
                    <input type="text" id={'displayName'} onChange={this.onChangeUsername} name={'displayName'}/>
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input type="password" id={'password'} onChange={this.onChangeUsername} name={'password'}/>
                </div>
                <div>
                    <label htmlFor="passwordRepeat">Repeat Password</label>
                    <input type="password" id={'passwordRepeat'} onChange={this.onChangeUsername}
                           name={'passwordRepeat'}/>
                </div>
                <div>
                    <input onChange={this.onChangeCheckbox} id={'agreed'} name={'agreed'} type="checkbox"/> Agreed
                    <br/>
                    <button onClick={this.submit} disabled={!this.state.agreed}>Sign Up</button>
                </div>
            </form>
        </div>);
    }

    onChangeUsername = event => {
        const {value, name} = event.target;
        this.setState({
            [name]: value
        });
    }
    onChangeCheckbox = event => {
        const {name, checked} = event.target;
        this.setState({
            [name]: checked
        });
    }
    submit = event => {
        const body = {
            username: this.state.username,
            displayName: this.state.displayName,
            password: this.state.password
        }
        event.preventDefault();
        axios.post('/api/1.0/users', body);
    }
}

export default UserSignUpPage;