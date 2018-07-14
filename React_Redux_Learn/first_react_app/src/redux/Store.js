export function counter(state, action) {
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