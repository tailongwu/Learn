import { createStore } from 'redux';
import React from 'react';
import { connect } from 'react-redux';

import ActionType from './ActionType';
import { stringFormat } from '../Utilities';

export class _ReduxTest extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const { value1, value2, factor1Add, factor1Minus, factor2Add, factor2Minus } = this.props;
        const content1 = stringFormat('Value1: {0}', value1);
        const content2 = stringFormat('Value2: {0}', value2);
        return (
            <div>
                <div>
                    {'Redux test:'}
                </div>
                <div>
                    <span>{content1}</span>
                    <button onClick={() => { factor1Add() }}>+</button>
                    <button onClick={() => { factor1Minus() }}>-</button>
                </div>
                <div>
                    <span>{content2}</span>
                    <button onClick={() => { factor2Add() }}>+</button>
                    <button onClick={() => { factor2Minus() }}>-</button>
                </div>
            </div>
        );
    }
}
function mapStateToProps(state) {
    return {
        value1: state.factor1.value,
        value2: state.factor2.value
    };
}
function mapDispatchToProps(dispatch) {
    return {
        factor1Add: () => dispatch({ type: ActionType.Factor1Add }),
        factor1Minus: () => dispatch({ type: ActionType.Factor1Minus }),
        factor2Add: () => dispatch({ type: ActionType.Factor2Add }),
        factor2Minus: () => dispatch({ type: ActionType.Factor2Minus })
    };
}
const ReduxTest = connect(mapStateToProps, mapDispatchToProps)(_ReduxTest);
export default ReduxTest;