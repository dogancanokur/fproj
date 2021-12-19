import React, {Component} from 'react';
import {signUp} from "./apiCall";

class UserSignUpPage extends Component {
    state = {
        username: "",
        displayName: "",
        password: "",
        agreed: false,
        pendingApiCall: false,
        errors: {
            username: "",
            password: "",
            displayName: ""
        }
    }

    render() {
        const {pendingApiCall, errors} = this.state;
        return (<div className={"container"}>
            <h1>Sign Up</h1>
            <form>
                <div className={"form-group"}>
                    <label htmlFor="username">Username</label>
                    <input className={errors.username ? "form-control is-invalid" : "form-control"} type="text"
                           id={'username'} name={'username'}
                           onChange={this.onChangeUsername}/>
                    <div className={"invalid-feedback"}>{errors.username}</div>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="displayName">Display Name</label>
                    <input className={errors.displayName ? "form-control is-invalid" : "form-control"} type="text"
                           id={'displayName'} onChange={this.onChangeUsername}
                           name={'displayName'}/>
                    <div className={"invalid-feedback"}>{errors.displayName}</div>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="password">Password</label>
                    <input className={errors.password ? "form-control is-invalid" : "form-control"} type="password"
                           id={'password'} onChange={this.onChangeUsername}
                           name={'password'}/>
                    <div className={"invalid-feedback"}>{errors.password}</div>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="passwordRepeat">Repeat Password</label>
                    <input className={"form-control"} type="password"
                           id={'passwordRepeat'}
                           onChange={this.onChangeUsername}
                           name={'passwordRepeat'}/>
                </div>
                <div>
                    <input onChange={this.onChangeCheckbox} id={'agreed'} name={'agreed'}
                           type="checkbox"/> <label htmlFor={"agreed"}>Agreed</label>
                    <button className={"btn btn-primary ml-2"} onClick={this.submit}
                            disabled={pendingApiCall || !this.state.agreed}>
                        {pendingApiCall && <span className={'spinner-border spinner-border-sm mr-1'}></span>}
                        Sign Up
                    </button>
                </div>
            </form>
        </div>);
    }

    onChangeUsername = event => {
        const {value, name} = event.target;
        const errors = {...this.state.errors};
        errors[name] = undefined;
        this.setState({
            errors,
            [name]: value
        });
    }
    onChangeCheckbox = event => {
        const {name, checked} = event.target;
        this.setState({
            [name]: checked
        });
    }
    submit = async event => {
        event.preventDefault();
        this.setState({pendingApiCall: true});
        const body = {
            username: this.state.username,
            displayName: this.state.displayName,
            password: this.state.password
        }
        try {
            const response = await signUp(body);

        } catch (e) {
            if (e.response.data.validationErrors) {
                const {validationErrors} = e.response.data;
                this.setState({
                    errors: validationErrors
                });
            }
        }
        this.setState({
            pendingApiCall: false
        });
    }
}

export default UserSignUpPage;