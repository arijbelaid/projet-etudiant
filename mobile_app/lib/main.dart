import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Gestion des étudiants',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const StudentListPage(),
    );
  }
}

// Modèle Etudiant
class Etudiant {
  final int id;
  final String cin;
  final String nom;
  final DateTime dateNaissance;

  Etudiant({
    required this.id,
    required this.cin,
    required this.nom,
    required this.dateNaissance,
  });

  factory Etudiant.fromJson(Map<String, dynamic> json) {
    return Etudiant(
      id: json['id'],
      cin: json['cin'],
      nom: json['nom'],
      dateNaissance: DateTime.parse(json['dateNaissance']),
    );
  }
}

// Service API
class ApiService {
  // Pour émulateur Android
  static const String baseUrl = 'http://localhost:8080/api/etudiants';
  // Pour téléphone physique (décommente et mets ton IP)
  // static const String baseUrl = 'http://192.168.1.x:8080/api/etudiants';

  Future<List<Etudiant>> fetchEtudiants() async {
    try {
      final response = await http.get(
        Uri.parse(baseUrl),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        List<dynamic> body = json.decode(response.body);
        return body.map((json) => Etudiant.fromJson(json)).toList();
      } else {
        throw Exception('Erreur: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Impossible de charger les étudiants: $e');
    }
  }
}

// Page principale avec la liste
class StudentListPage extends StatefulWidget {
  const StudentListPage({super.key});

  @override
  State<StudentListPage> createState() => _StudentListPageState();
}

class _StudentListPageState extends State<StudentListPage> {
  late Future<List<Etudiant>> futureEtudiants;
  final ApiService apiService = ApiService();

  @override
  void initState() {
    super.initState();
    futureEtudiants = apiService.fetchEtudiants();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Liste des Étudiants',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
        centerTitle: true,
        elevation: 4,
      ),
      body: RefreshIndicator(
        onRefresh: () async {
          setState(() {
            futureEtudiants = apiService.fetchEtudiants();
          });
        },
        child: FutureBuilder<List<Etudiant>>(
          future: futureEtudiants,
          builder: (context, snapshot) {
            // En attente du chargement
            if (snapshot.connectionState == ConnectionState.waiting) {
              return const Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    CircularProgressIndicator(),
                    SizedBox(height: 16),
                    Text('Chargement des étudiants...'),
                  ],
                ),
              );
            }
            // Erreur
            else if (snapshot.hasError) {
              return Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Icon(
                      Icons.error_outline,
                      size: 64,
                      color: Colors.red,
                    ),
                    const SizedBox(height: 16),
                    Text(
                      'Erreur: ${snapshot.error}',
                      style: const TextStyle(color: Colors.red),
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: 16),
                    ElevatedButton(
                      onPressed: () {
                        setState(() {
                          futureEtudiants = apiService.fetchEtudiants();
                        });
                      },
                      child: const Text('Réessayer'),
                    ),
                  ],
                ),
              );
            }
            // Succès - Affichage de la liste
            else if (snapshot.hasData) {
              final etudiants = snapshot.data!;
              if (etudiants.isEmpty) {
                return const Center(
                  child: Text('Aucun étudiant trouvé'),
                );
              }
              return ListView.builder(
                itemCount: etudiants.length,
                itemBuilder: (context, index) {
                  final etudiant = etudiants[index];
                  return Card(
                    margin: const EdgeInsets.symmetric(
                      horizontal: 16,
                      vertical: 8,
                    ),
                    elevation: 3,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: ListTile(
                      contentPadding: const EdgeInsets.all(16),
                      leading: CircleAvatar(
                        backgroundColor: Colors.blue,
                        radius: 25,
                        child: Text(
                          etudiant.nom.substring(0, 1).toUpperCase(),
                          style: const TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                            color: Colors.white,
                          ),
                        ),
                      ),
                      title: Text(
                        etudiant.nom,
                        style: const TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      subtitle: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const SizedBox(height: 8),
                          Row(
                            children: [
                              const Icon(
                                Icons.badge,
                                size: 16,
                                color: Colors.grey,
                              ),
                              const SizedBox(width: 8),
                              Text('CIN: ${etudiant.cin}'),
                            ],
                          ),
                          const SizedBox(height: 4),
                          Row(
                            children: [
                              const Icon(
                                Icons.cake,
                                size: 16,
                                color: Colors.grey,
                              ),
                              const SizedBox(width: 8),
                              Text(
                                'Date de naissance: ${etudiant.dateNaissance.day}/${etudiant.dateNaissance.month}/${etudiant.dateNaissance.year}',
                              ),
                            ],
                          ),
                        ],
                      ),
                      trailing: const Icon(
                        Icons.chevron_right,
                        color: Colors.blue,
                      ),
                      isThreeLine: true,
                    ),
                  );
                },
              );
            }
            // Autre cas
            else {
              return const Center(
                child: Text('Aucune donnée disponible'),
              );
            }
          },
        ),
      ),
    );
  }
}