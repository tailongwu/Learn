import React from 'react';
import ReactDOM from 'react-dom';
import { createStore } from 'redux';
import './index.css';
// import App from './app/App';
import Test from './test/Test';
import registerServiceWorker from './registerServiceWorker';
import ReduxTest from './redux/ReduxTest';
import Provider from 'react-redux/lib/components/Provider';

function counter(state, action) {
    state = state || { value: 0 };
    switch (action.type) {
        case 'ADD':
            return {
                value: state.value + 1
            };
        case 'MINUS':
            return {
                value: state.value - 1
            };
        default:
            return state.value;
    }
}

var store = createStore(counter);
ReactDOM.render((
    <div>
        <Test />
        <Provider store={store}>
            <ReduxTest />
        </Provider>
    </div>
), document.getElementById('root'));
registerServiceWorker();
