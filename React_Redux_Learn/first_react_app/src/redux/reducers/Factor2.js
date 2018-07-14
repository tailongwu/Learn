import ActionType from '../ActionType';

export default function factor2(state, action) {
    state = state || { value: 0 };
    switch (action.type) {
        case ActionType.Factor2Add:
            return {
                value: state.value + 1
            };
        case ActionType.Factor2Minus:
            return {
                value: state.value - 1
            };
        default:
            return state;
    }
}