const express = require('express');
const router = express.Router();
const db = require('../database');
// VULNERABLE A XSS ALMACENADO (almacena datos sin sanitizar)
router.post('/', (req, res) => {
    const { name, email, password } = req.body;
    const sql = 'INSERT INTO users (name, email, password) VALUES (?,?,?)';
    db.run(sql, [name, email, password], function (err) {
        if (err) {
            return res.status(500).json({ error: err.message });
        }
        res.status(201).json({ id: this.lastID, name, email });
    });
});

router.get('/:id', (req, res) => {
    console.log("hola");
    console.log(req.params.id);
    const sql = 'SELECT * FROM users WHERE id = ?';
    db.get(sql, [req.params.id], (err, row) => {
        if (err) {
            console.log(err);
            return res.status(500).json({ error: err.message });
        }
        console.log(row);
        if (!row) {
            return res.status(404).json({ error: 'Usuario no encontrado' });
        }
        res.json(row);
    });
});
module.exports = router;
