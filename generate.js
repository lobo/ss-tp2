function randomInRange(min, max) {
  return Math.random() < 0.5 ? ((1-Math.random()) * (max-min) + min) : (Math.random() * (max-min) + min);
}

function randomN(low, high) {
    return Math.random() * (high - low) + low
}


// GENERATION
console.log();
console.log(randomInRange() + " " + );

n_of_moments = randomN(0, 1000);

for (let index = 0; index < n_of_moments.length; index++) {
    console.log(index);
    console.log(randomInRange() + " " + randomInRange() + " " + randomInRange(-180,180));
    
    
}