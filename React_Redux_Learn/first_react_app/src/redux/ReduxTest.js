import { createStore } from 'redux';
import React from 'react';
import { connect } from 'react-redux';



export class _ReduxTest extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const { value, add, minus } = this.props;
        return (
            <div>
                <div>
                    {'Redux test'}
                </div>
                <div>
                    {value}
                </div>
                <div>
                    <button onClick={add}>
                        +
                    </button>
                    <div>{this.props.valur}</div>
                </div>
            </div>
        );
    }
}
function mapStateToProps(state) {
    return {
        value: state.value
    };
}
function mapDispatchToProps(dispatch) {
    return {
        add: () => dispatch({ type: 'ADD' }),
        minus: () => dispatch({ type: 'MINUS' })
    };
}
const ReduxTest = connect(mapStateToProps, mapDispatchToProps)(_ReduxTest);
export default ReduxTest;