import ActionType from '../ActionType';

export default function factor1(state, action) {
    state = state || { value: 0 };
    switch (action.type) {
        case ActionType.Factor1Add:
            return {
                value: state.value + 1
            };
        case ActionType.Factor1Minus:
            return {
                value: state.value - 1
            };
        default:
            return state;
    }
}